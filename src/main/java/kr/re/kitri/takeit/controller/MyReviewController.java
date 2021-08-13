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

import dao.ReviewDAO;
import vo.ReviewVO;


@WebServlet("/myreview")
public class MyReviewController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("takeit-userid");
		
		ReviewDAO rdao = new ReviewDAO();
		List<ReviewVO> rlist = rdao.selectUserReview(id);
		JsonArray jsonArr = new JsonArray();
		
		for(ReviewVO rvo : rlist) {
			JsonObject json = new JsonObject();
			json.addProperty("className", rvo.getClassName());
			json.addProperty("reviewDate", String.valueOf(rvo.getReviewDate()));
			json.addProperty("recommend", rvo.getRecommend());
			json.addProperty("category", rvo.getRecommend());
			
			jsonArr.add(json);
		}
		Gson gson = new Gson();
		String jsonResponse = gson.toJson(jsonArr);
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(jsonResponse);
	}

}
