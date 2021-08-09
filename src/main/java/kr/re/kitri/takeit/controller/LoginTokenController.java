package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import loginapi.KakaoAPI;

@WebServlet(urlPatterns = { "/login/kakao", "/login/naver", "/login/google" })
public class LoginTokenController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	String requestURI = request.getRequestURI();
	System.out.println("requestURI : " + requestURI);

	String uri = "";
	if (requestURI.contains("kakao")) {
	    uri = "kakao";
	    String code = request.getParameter("code");
	    System.out.println("LoginController code 인가 코드: " + code);
	    KakaoAPI kakaoApi = new KakaoAPI();
	    String accessToken = kakaoApi.getAccessToken(code);
	    System.out.println("accessToken : " + accessToken);

	    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
	    rd.forward(request, response);

	} else if (requestURI.contains("naver")) {
	    uri = "naver";
	} else if (requestURI.contains("kakao")) {
	    uri = "google";
	}
	System.out.println("Login Token Servlet, uri : " + uri);

    }

}
