package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/myfavorite")
public class MyFavoriteController extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("takeit-userid");

		// ClassDAO cdao = new ClassDAO();
		// List<ClassVO> clist = cdao.selectFavoriteClass(id);
		// JsonArray jsonArr = new JsonArray();
		//
		// for(ClassVO cvo : clist) {
		//
		// JsonObject json = new JsonObject();
		// json.addProperty("classId", cvo.getClassId());
		// json.addProperty("className", cvo.getClassName());
		// json.addProperty("creater", cvo.getCreater());
		// json.addProperty("favorite", cvo.getFavorite());
		// json.addProperty("openDate", String.valueOf(cvo.getOpenDate()));
		//
		// jsonArr.add(json);
		// }
		// Gson gson = new Gson();
		// String jsonResponse = gson.toJson(jsonArr);
		//
		// response.setContentType("text/html; charset=utf-8");
		// PrintWriter out = response.getWriter();
		// out.print(jsonResponse);
	}

}
