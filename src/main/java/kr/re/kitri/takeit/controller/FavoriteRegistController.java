package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import dao.FavoriteDAO;

@WebServlet("/favorite-regist")
public class FavoriteRegistController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		String userId = (String) session.getAttribute("takeit_userid");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");

		FavoriteDAO fdao = new FavoriteDAO();

		int classId = Integer.parseInt(request.getParameter("classId"));
		System.out.println("classId : " + classId);

		int favoriteResult = fdao.insertFavorite(userId, classId);
		int classResult;
		System.out.println(favoriteResult);
		if (favoriteResult == 1) {
			classResult = fdao.updateFavorite(classId);
			Gson gson = new Gson();

			String result = gson.toJson(classResult);
			System.out.println(result);
			response.setContentType("application/json; charset=utf8");
			response.getWriter().write(result);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}