package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/mypage/openclass")
public class openClassController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/openclass/open-class.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//강의 개설
//		HttpSession session = request.getSession();
//		String id = (String)session.getAttribute("id");
		
		String className = request.getParameter("className");
		String category = request.getParameter("category");
		String classType = request.getParameter("classType");
		
		String classPeriod = "-";
		String classScheduleNum = "-";
		String classSchedule = "-";
		String classCapacity = "-";
		if(classType.equals("on")){
			classPeriod = request.getParameter("classPeriod");
		}else if(classType.equals("off")){
			classScheduleNum = request.getParameter("classScheduleNum");
			classSchedule = request.getParameter("classSchedule");
			classCapacity = request.getParameter("classCapacity");
		}
		String classFee = request.getParameter("classFee");
		String classDetail = request.getParameter("classDetail");
		
		System.out.println(className);
		System.out.println(category);
		System.out.println(classType);
		System.out.println(classPeriod);
		System.out.println(classScheduleNum);
		System.out.println(classSchedule);
		System.out.println(classCapacity);
		System.out.println(classFee);
		System.out.println(classDetail);
	}

}
