package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import dao.ClassDAO;
import dao.FavoriteDAO;
import vo.ClassVO;


@WebServlet("/select-class")
public class SelectClassController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		ClassDAO cdao = new ClassDAO();
		
		int classId = Integer.parseInt(request.getParameter("classId"));
		
		List<ClassVO> clist = cdao.selectDetail(classId);
		
		Gson gson = new Gson();
		String result = gson.toJson(clist);
		
		response.setContentType("application/json; charset=utf8");
		response.getWriter().write(result);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
