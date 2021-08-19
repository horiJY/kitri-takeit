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

@WebServlet("/paging")
public class PagingController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		ClassDAO cdao = new ClassDAO();
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");

		String category = request.getParameter("category");
		String range = request.getParameter("range");
		String type = request.getParameter("type");
		int start = Integer.parseInt(request.getParameter("start"));
		int end = Integer.parseInt(request.getParameter("end"));
		
		List<ClassVO> clist = cdao.selectClassPage(category, range, type, start, end);
		
		Gson gson = new Gson();
		String result = gson.toJson(clist);
		
		response.setContentType("application/json; charset=utf8");
		response.getWriter().write(result);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
