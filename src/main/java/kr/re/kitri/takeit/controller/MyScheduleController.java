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

import dao.ScheduleDAO;
import vo.CalendarJson;
import vo.ScheduleVO;

@WebServlet("/mypage/schedule")
public class MyScheduleController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//HttpSession session = request.getSession();
		//String userid = (String)session.getAttribute("takeit-id");
		
		//select schedule
		ScheduleDAO sdao = new ScheduleDAO();
		//List<ScheduleVO> slist = sdao.selectSchedule(userid);
		List<ScheduleVO> slist = new ArrayList<ScheduleVO>();
		{
			ScheduleVO svo = new ScheduleVO();
			svo.setClassId(1);
			svo.setStartTime("2021-08-26 10:00");
			svo.setEndTime("2021-08-26 11:00");
			slist.add(svo);
		}
		{
			ScheduleVO svo = new ScheduleVO();
			svo.setClassId(1);
			svo.setStartTime("2021-08-31 10:00");
			svo.setEndTime("2021-08-31 11:00");
			slist.add(svo);
		}
		{
			ScheduleVO svo = new ScheduleVO();
			svo.setClassId(1);
			svo.setStartTime("2021-09-01 10:00");
			svo.setEndTime("2021-09-01 11:00");
			slist.add(svo);
		}
		
		List<CalendarJson> clist = new ArrayList<CalendarJson>();
		for(ScheduleVO svoo:slist) {
			CalendarJson cJson = new CalendarJson();
			cJson.scheduleToCalendar(svoo);
			clist.add(cJson);
		}
		//조회된 스케쥴 전송
		Gson gson = new Gson();
		String json = gson.toJson(clist);
		request.setAttribute("cJson", json);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/myschedule.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//선택한 강의 정보로 이동
	}

}
