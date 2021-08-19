package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

<<<<<<< HEAD
import dao.FavoriteDAO;
=======
import dao.AssignmentDAO;
import dao.ClassDAO;
>>>>>>> refs/remotes/takeit/경하
import vo.ClassVO;

@WebServlet("/myassignment")
public class MyAssignmentController extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("takeit-userid");
<<<<<<< HEAD

		FavoriteDAO fdao = new FavoriteDAO();
		List<ClassVO> clist = fdao.selectFavoriteClass(id);

=======
		
		AssignmentDAO adao = new AssignmentDAO();
		List<ClassVO> clist = adao.selectAssignmentClass(id);
		
>>>>>>> refs/remotes/takeit/경하
		JsonArray jsonArr = new JsonArray();

		for (ClassVO cvo : clist) {
			JsonObject json = new JsonObject();
			json.addProperty("className", cvo.getClassName());
			json.addProperty("creater", cvo.getCreater());
			json.addProperty("classType", cvo.getClassType());
			json.addProperty("recommend", cvo.getRecommend());
			json.addProperty("category", cvo.getCategory());

			jsonArr.add(json);
		}

		System.out.println(jsonArr);
		Gson gson = new Gson();
		String jsonResponse = gson.toJson(jsonArr);

		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(jsonResponse);

	}

}
