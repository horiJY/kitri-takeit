package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/assignment/check")
public class AssignmentRegistCheckController extends HttpServlet {
	//
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//스케쥴 중복 체크
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("takeit-userid");
		ScheduleDAO sdao = new ScheduleDAO();
		int result = 0;
		for(int i=0;i<slist.length();i++){
			String stime = slist.get(i).getStartTime();
			String etime = slist.get(i).getEndTime();
			result = selectScheduleOverlap(userId,stime,etime);
			if(result>0){
				//중복 일정 존재
				response.getWriter().println("OVERLAP");
				return;
			}
		}
		//중복 시 해당 마이페이지>수강/강의 내역으로 이동
	}

}
