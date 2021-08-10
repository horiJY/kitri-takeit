package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import vo.UserVO;


@WebServlet("/myinfo-update")
public class MyInfoUpdateController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		UserVO uvo = new UserVO();
		uvo.setUserId(id);
		uvo.setUserName(name);
		uvo.setPhone(phone);
		UserDAO udao = new UserDAO();
		udao.updateUser(uvo);
		
		response.sendRedirect(request.getContextPath()+"/mypage");
		
	}

}
