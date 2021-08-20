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

import dao.ClassDAO;
import dao.ScheduleDAO;
import vo.ClassVO;
import vo.ScheduleVO;

@SuppressWarnings("serial")
@WebServlet("/mypage/openclass")
public class OpenClassController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/open-class.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// json 데이터 파싱
		Gson gson = new Gson();
		ClassVO cvo = gson.fromJson(request.getParameter("class"), ClassVO.class);
		cvo.setType("P");

		JsonObject sJson = gson.fromJson(request.getParameter("schedule"), JsonObject.class);
		JsonArray sJarr = sJson.get("classSchedule").getAsJsonArray();
		List<ScheduleVO> slist = new ArrayList<ScheduleVO>();
		for (int i = 0; i < sJarr.size(); i++) {
			ScheduleVO svo = new Gson().fromJson(sJarr.get(i), ScheduleVO.class);
			slist.add(svo);
		}
		// 이미 존재하는 스케쥴과 겹치지 않는 지 확인
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("takeit_userid");
		ScheduleDAO sdao = new ScheduleDAO();
		int result = 0;
		for (int i = 0; i < slist.size(); i++) {
			String stime = slist.get(i).getStartTime();
			String etime = slist.get(i).getEndTime();
			result = sdao.selectScheduleOverlap(userId, stime, etime);
			if (result > 0) {
				// 중복 일정 존재
				response.getWriter().println("OVERLAP");
				return;
			}
		}
		ClassDAO cdao = new ClassDAO();
		int classId = cdao.insertClass(cvo);
		if (classId == 0) {
			// 클래스 insert 실패
			response.getWriter().println("FAIL");
			return;
		}

		result = sdao.insertSchedule(slist);
		if (result != sJarr.size()) {
			// 앞에서 추가한 클래스 폐지
			// ->update class' type to closed
			// 스케쥴 insert 실패 응답
			response.getWriter().println("FAIL");
			return;
		}
		// opendate 설정

		// 강의 개설 성공 응답
		response.getWriter().println("SUCCESS");
	}
}
