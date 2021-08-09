package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import dao.UserDAO;
import json.UserJson;
import vo.UserVO;

@WebServlet("/mypage")
public class MyPageController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		if(id==null) {
			
			UserJson uj = new UserJson();
			uj.setUserId("test");
			uj.setPw("1234");
			uj.setUserName("테스트");
			uj.setUserType("C");
			uj.setPhone("01000000000");
			uj.setPoint(100);
			
			
			request.setAttribute("uvo", uj);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/mypage.jsp");
			rd.forward(request, response);
//			response.sendRedirect(request.getContextPath()+"/member/login");
		} else {
			UserDAO udao = new UserDAO();
			UserVO uvo = udao.selectUserInfo(id);
			
			UserJson uj = new UserJson();
			uj.setUserId(uvo.getUserId());
			uj.setPw(uvo.getPw());
			uj.setUserName(uvo.getUserName());
			uj.setUserType(uvo.getUserType());
			uj.setPhone(uvo.getPhone());
			uj.setPoint(uvo.getPoint());
			
			
			request.setAttribute("uvo", uj);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/mypage.jsp");
			rd.forward(request, response);
			
		}
		
		
		
	}

}
