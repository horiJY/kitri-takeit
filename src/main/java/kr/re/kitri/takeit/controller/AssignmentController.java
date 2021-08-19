package controller;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AssignmentDAO;
import vo.AssignmentVO;
import vo.ClassVO;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/AssingmentController")
public class AssignmentController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String id = request.getParameter("p_userid");
		
		//int c_id = Integer.parseInt(request.getParameter("c_id"));
	int 	c_id = 1;
		
//		System.out.print(userName);
		ClassVO cvo =null;
		AssignmentVO avo = null;
		AssignmentDAO adao = new AssignmentDAO();
		avo = adao.assignmentSelect(id);
		cvo= adao.assignmentPaySelect(c_id);
		request.setAttribute("AVO", avo);
		request.setAttribute("CVO", cvo);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/assignment.jsp"); //jsp로 보내줌
		rd.forward(request, response);
		
		
		//AssignmentDAO  adao = new AssignmentDAO()
		
	
	
	
		
		
		
		
		
		
		
		
	}

}
