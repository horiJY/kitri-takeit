package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import dao.ClassDAO;
import dao.FavoriteDAO;
 
@WebServlet("/favorite")
public class FavoriteController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		String userId = (String) session.getAttribute("takeit_userid");
		// String userId = "testCreater0";
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");

		ClassDAO cdao = new ClassDAO();
		FavoriteDAO fdao = new FavoriteDAO();

		int classId = Integer.parseInt(request.getParameter("classId"));

		int favoriteResult = fdao.selectFavorite(userId, classId);

		Gson gson = new Gson();

		String result = gson.toJson(favoriteResult);

		response.setContentType("application/json; charset=utf8");
		response.getWriter().write(result);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
