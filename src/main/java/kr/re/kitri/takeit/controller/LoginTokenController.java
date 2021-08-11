package controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.WebUserDAO;
import service.KakaoLoginService;

@WebServlet(urlPatterns = { "/login/kakao", "/login/naver", "/login/google" })
public class LoginTokenController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void service(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	String requestURI = request.getRequestURI();
	System.out.println("requestURI : " + requestURI);
	HashMap<String, String> UserInfo = null; // 로그인유저의 아이디,이름,프로필썸네일 / id,nickname,thumnailURL

	if (requestURI.contains("kakao")) {
	    // 카카오로그인 후 인가코드 받기
	    String code = request.getParameter("code");
	    // 인가코드로 엑세스토큰 발급
	    KakaoLoginService kakaoApi = new KakaoLoginService();
	    String accessToken = kakaoApi.getAccessToken(code);
	    // 엑세스토큰으로 유저정보 가져와 DB확인
	    UserInfo = kakaoApi.getUserInfo(accessToken);
	    WebUserDAO webuserdao = new WebUserDAO();
	    // webuser 테이블에 로그인 유저 id조회 후 없을 시 등록
	    if (!webuserdao.getWebUser(UserInfo.get("id"))) {
		webuserdao.setWebUser(UserInfo);
		System.out.println("카카오 신규유저 생성");
	    }
	} else if (requestURI.contains("naver")) {

	} else if (requestURI.contains("kakao")) {

	}

	System.out.println(UserInfo);
	HttpSession session = request.getSession();
	session.setAttribute("takeit-userid", UserInfo.get("id"));
	session.setAttribute("takeit-username", UserInfo.get("nickname"));
	session.setAttribute("takeit-userthumnail", UserInfo.get("thumnailURL"));

	response.sendRedirect("http://localhost:8080/takeit_prj/main");
    }

}
