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
		System.out.println("aaaaa");
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("takeit-userid");
		System.out.println("id : "+id);
		if(id==null) {
			id="k1234567894";	//'C'
//			id="k1489859146";	//'U'
//			response.sendRedirect(request.getContextPath()+"/member/login");
			UserDAO udao = new UserDAO();
			UserVO uvo = udao.selectUserInfo(id);
			
			session.setAttribute("takeit-userid", id);
			request.setAttribute("uvo", uvo);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/mypage.jsp");
			rd.forward(request, response);
		} else {
			UserDAO udao = new UserDAO();
			UserVO uvo = udao.selectUserInfo(id);
			
			session.setAttribute("takeit-userid", id);
			request.setAttribute("uvo", uvo);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/mypage.jsp");
			rd.forward(request, response);
		}
		
	}

}
