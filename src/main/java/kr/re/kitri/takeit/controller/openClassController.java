package controller;

import java.io.IOException;
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
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import vo.ClassVO;
import vo.ScheduleVO;

@SuppressWarnings("serial")
@WebServlet("/mypage/openclass")
public class openClassController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/openclass/open-class.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//json 데이터 파싱
		Gson gson = new Gson();
		
		ClassVO cvo = gson.fromJson(request.getParameter("class"),ClassVO.class);
		System.out.println(cvo.getClassName());
		
		JsonObject sJson = gson.fromJson(request.getParameter("schedule"),JsonObject.class);
		JsonArray sJarr = sJson.get("classSchedule").getAsJsonArray();
		List<ScheduleVO> slist = new ArrayList<ScheduleVO>();
		for(int i=0;i<sJarr.size();i++) {
			ScheduleVO svo = new Gson().fromJson(sJarr.get(i),ScheduleVO.class);
			slist.add(svo);
		}
		System.out.println(slist.get(sJarr.size()-1).getDay());

		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		//강의 개설
		//강의 일정 추가
		
	}

}
