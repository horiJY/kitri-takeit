package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import dao.ClassDAO;
import vo.ClassVO;

@WebServlet("/range")
public class MainRangeController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String category = request.getParameter("category");
		String range = request.getParameter("range");
		
		System.out.println(category);
		System.out.println(range);
		
		ClassDAO cdao = new ClassDAO();
		List<ClassVO> clist = cdao.selectClassList(category, range);
		
		Gson gson = new Gson();
		String result = gson.toJson(clist);
		
		//request.setAttribute("category", category);
		//request.setAttribute("range", range);
		
		//RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
		//rd.forward(request, response);
		
		response.setContentType("application/json; charset=utf8");
		response.getWriter().write(result);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
