package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ClassDAO;
import dao.FavoriteDAO;

@WebServlet("/favorite-regist")
public class FavoriteRegistController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String userId = (String)session.getAttribute("useId");
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		ClassDAO cdao = new ClassDAO();
		FavoriteDAO fdao = new FavoriteDAO();

		int classId = Integer.parseInt(request.getParameter("classId"));
		
		int favoriteResult = fdao.insertFavorite(userId, classId);
		int classResult = cdao.updateFavorite(classId);
		
		response.setContentType("application/json; charset=utf8");
		response.getWriter().write(classResult);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
