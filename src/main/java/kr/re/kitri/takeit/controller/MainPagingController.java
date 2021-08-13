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

import dao.ClassDAO;

@WebServlet("/paging-main")
public class MainPagingController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		ClassDAO cdao = new ClassDAO();
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String category = request.getParameter("category");
	
		String classCnt = Integer.toString(cdao.selectClassCnt(category));
		
		//request.setAttribute("classCnt", classCnt);
		
		Gson gson = new Gson();
		String result = gson.toJson(classCnt);
		
		response.setContentType("application/json; charset=utf8");
		response.getWriter().write(result);
		
//		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/main.js");
//		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
