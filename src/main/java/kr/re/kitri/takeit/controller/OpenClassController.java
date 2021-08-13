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
public class OpenClassController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/openclass/open-class.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//json 데이터 파싱
		Gson gson = new Gson();
		
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("takeit-userid");
		
		ClassVO cvo = gson.fromJson(request.getParameter("class"),ClassVO.class);
		cvo.setCreater(userId);
		int classId = -1;
		//int result = ClassDAO.insertClass(cvo);
		//if(result<0){ 클래스 개설 실패 응답 }
		//select
		
		JsonObject sJson = gson.fromJson(request.getParameter("schedule"),JsonObject.class);
		JsonArray sJarr = sJson.get("classSchedule").getAsJsonArray();
		List<ScheduleVO> slist = new ArrayList<ScheduleVO>();
		for(int i=0;i<sJarr.size();i++) {
			ScheduleVO svo = new Gson().fromJson(sJarr.get(i),ScheduleVO.class);
			svo.setClassId(classId);
			slist.add(svo);
		}
		//이미 존재하는 스케쥴과 겹치지 않는 지 확인
		
		
		//int result = ScheduleDVO.insertSchedule(slist);
		//if (result<sJarr.size()){ 실패 응답}
		
		//강의 개설 성공 응답
		
	}

}
