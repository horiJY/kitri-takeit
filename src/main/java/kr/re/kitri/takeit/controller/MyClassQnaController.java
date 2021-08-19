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

import dao.ClassQnaDAO;
import vo.ClassQnaVO;


@WebServlet("/myclassqna")
public class MyClassQnaController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("takeit-userid");
		
		ClassQnaDAO cqdao = new ClassQnaDAO();
		List<ClassQnaVO> cqlist = cqdao.selectMyClassQna(id);
		JsonArray jsonArr = new JsonArray();
		
		for(ClassQnaVO cqvo : cqlist) {
			JsonObject json = new JsonObject();
			json.addProperty("className", cqvo.getClassName());
			json.addProperty("qnaTitle", cqvo.getQnaTitle());
			json.addProperty("qnaDate", String.valueOf(cqvo.getQnaDate()));
			json.addProperty("userId", cqvo.getUserId());
			json.addProperty("question", cqvo.getQuestion());
			json.addProperty("answer", cqvo.getAnswer());
			
			jsonArr.add(json);
		}
		Gson gson = new Gson();
		String jsonResponse = gson.toJson(jsonArr);
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(jsonResponse);
	}

}
