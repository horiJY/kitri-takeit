package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import dao.UserDAO;
import vo.UserVO;
 

@WebServlet("/myinfoedit")
public class MyInfoEditController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("takeit_userid");
		
		System.out.println("id="+id);
		UserDAO udao = new UserDAO();
		UserVO uvo = udao.selectUserInfo(id);
		
		JsonObject json = new JsonObject();
		json.addProperty("thumnail", uvo.getUserThumnail());
		json.addProperty("id", uvo.getUserId());
		json.addProperty("name", uvo.getUserName());
		json.addProperty("phone", uvo.getPhone());
		json.addProperty("userType", uvo.getUserType());
		
		Gson gson = new Gson();
		String jsonResponse = gson.toJson(json);
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(jsonResponse);
		
	}

}
