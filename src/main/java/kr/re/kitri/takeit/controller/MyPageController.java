package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import dao.UserDAO;
import vo.UserVO;

@WebServlet("/mypage")
public class MyPageController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("takeit-userid");
		if(id==null) {
			id="k1234567894";	//'C'
//			id="jytest0110@gmail.com";	//'U'
//			id="hori_q@naver.com";	//'A'
			UserDAO udao = new UserDAO();
			UserVO uvo = udao.selectUserInfo(id);
			System.out.println(uvo.getUserType());
			session.setAttribute("takeit-userid", id);
			session.setAttribute("userType", uvo.getUserType());
			request.setAttribute("uvo", uvo);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/mypage.jsp");
			rd.forward(request, response);
//			response.sendRedirect(request.getContextPath()+"/member/login");
		} else {
			id="k1234567894";	//'C'
//			id="jytest0110@gmail.com";	//'U'
//			id="hori_q@naver.com";	//'A'
			UserDAO udao = new UserDAO();
			UserVO uvo = udao.selectUserInfo(id);
			
			System.out.println(uvo.getUserType());
			session.setAttribute("takeit-userid", id);
			session.setAttribute("userType", uvo.getUserType());
			request.setAttribute("uvo", uvo);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/mypage.jsp");
			rd.forward(request, response);
		}
		
	}

}
