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

import dao.QnaDAO;
import vo.QnaVO;

@WebServlet("/admin-qna")
public class AdminQnaController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	} 

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("takeit_userid");
		QnaDAO qdao = new QnaDAO();		
		List<QnaVO> qlist = qdao.selectAllQna();
		JsonArray jsonArr = new JsonArray();
		for(QnaVO qvo : qlist) {
			JsonObject json = new JsonObject();
			json.addProperty("qnaTitle", qvo.getQnaTitle());
			json.addProperty("qnaDate", String.valueOf(qvo.getQnaDate()));
			json.addProperty("userId", qvo.getUserId());
			json.addProperty("question", qvo.getQuestion());
			json.addProperty("answer", qvo.getAnswer());
			
			jsonArr.add(json);
		}
		Gson gson = new Gson();
		String jsonResponse = gson.toJson(jsonArr);
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(jsonResponse);
	}

}
