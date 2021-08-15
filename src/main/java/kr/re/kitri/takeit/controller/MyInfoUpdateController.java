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

import dao.UserDAO;
import vo.UserVO;


@WebServlet("/userupdate")
public class MyInfoUpdateController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		UserVO uvo = new UserVO();
		uvo.setUserId(id);
		uvo.setUserName(name);
		uvo.setPhone(phone);
		
		UserDAO udao = new UserDAO();
		int result = udao.updateUser(uvo);
		
		JsonObject json = new JsonObject();
		if(result!=0) {
			json.addProperty("msg", "성공적으로 업데이트 되었습니다.");
		} else {
			json.addProperty("msg", "업데이트를 실패했습니다. 다시 시도해주세요.");
		}
		
		Gson gson = new Gson();
		String jsonResponse = gson.toJson(json);
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(jsonResponse);
		
	}

}