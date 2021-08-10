package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.KakaoAPI;

@WebServlet(urlPatterns = { "/login/kakao", "/login/naver", "/login/google" })
public class LoginTokenController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void service(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	String requestURI = request.getRequestURI();
	System.out.println("requestURI : " + requestURI);

	if (requestURI.contains("kakao")) {
	    String code = request.getParameter("code");
	    System.out.println("인가 코드: " + code);
	    KakaoAPI kakaoApi = new KakaoAPI();
	    String accessToken = kakaoApi.getAccessToken(code);

//	    request.setAttribute("accessToken", jsonToken);
//	    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
//	    rd.forward(request, response);

	} else if (requestURI.contains("naver")) {

	} else if (requestURI.contains("kakao")) {

	}

    }

}
