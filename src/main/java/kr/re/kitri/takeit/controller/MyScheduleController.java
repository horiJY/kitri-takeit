package controller;

import java.io.IOException;
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
import vo.ScheduleVO;

@WebServlet("/mypage/schedule")
public class MyScheduleController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String userid = (String)session.getAttribute("takeit-id");
		
		//select schedule
		ScheduleDAO sdao = new ScheduleDAO();
		List<ScheduleVO> slist = sdao.selectSchedule(userid);
		//조회된 스케쥴 전송
		Gson gson = new Gson();
		String json = gson.toJson(slist);
		request.setAttribute("sJson", json);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/myschedule.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//선택한 강의 정보로 이동
	}

}
