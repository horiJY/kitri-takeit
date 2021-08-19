package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import dao.ClassQnaDAO;
import dao.QnaDAO;
import vo.QnaVO;

@WebServlet("/myclass-qna-update")
public class MyClassQnaUpdateController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("takeit-userid");

		String qnaTitle = request.getParameter("qnaTitle");
		String answer = request.getParameter("answer");
		String className = request.getParameter("className");
		String userId = request.getParameter("userId");
		
		System.out.println("qnaTitle : " + qnaTitle + " answer: " + answer);
		
		ClassQnaDAO cqdao = new ClassQnaDAO();
		int result = cqdao.updateClassQna(qnaTitle, userId, answer, className);
		
		JsonObject json = new JsonObject();
		if(result!=0) {
			json.addProperty("code", "성공적으로 업데이트 되었습니다.");
			
		} else {
			json.addProperty("code", "업데이트를 실패했습니다. 다시 시도해주세요.");
		}
		
		Gson gson = new Gson();
		String jsonResponse = gson.toJson(json);
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(jsonResponse);
	}
}
