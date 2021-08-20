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

import dao.ClassDAO;
import vo.ClassVO;

@WebServlet("/mypreclass")
public class MyPreClassController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("takeit-userid");
		
		ClassDAO cdao = new ClassDAO();
		List<ClassVO> clist = cdao.selectMyPreClass(id);
		
		JsonArray jsonArr = new JsonArray();
		
		for(ClassVO cvo : clist) {
			JsonObject json = new JsonObject();
			json.addProperty("className", cvo.getClassName());
			json.addProperty("category", cvo.getCategory());
			json.addProperty("openDate", String.valueOf(cvo.getOpenDate()));
			json.addProperty("favorite", cvo.getFavorite());
			
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
