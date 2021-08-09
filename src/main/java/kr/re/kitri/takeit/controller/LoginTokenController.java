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
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doProcess(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doProcess(request, response);
  }

  protected void doProcess(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	String requestURI = request.getRequestURI();
	System.out.println("requestURI : " + requestURI);

	if (requestURI.contains("kakao")) {
	    String code = request.getParameter("code");
	    System.out.println("LoginController code 인가 코드: " + code);
	    KakaoAPI kakaoApi = new KakaoAPI();
        kakaoApi.getAccessToken(request, response);
        String jsonToken = request.getParameter("jsonToken");
        System.out.println("responsejson : " + jsonToken);

	    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
	    rd.forward(request, response);

	} else if (requestURI.contains("naver")) {

	} else if (requestURI.contains("kakao")) {

	}

    }

}
