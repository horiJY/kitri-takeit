package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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

import dao.AssignmentDAO;
import dao.ClassDAO;
import dao.ScheduleDAO;
import dao.UserDAO;
import vo.AssignmentVO;
import vo.ClassVO;
import vo.ScheduleVO;

@WebServlet("/assignment/regist")
public class AssignmentRegistController extends HttpServlet {
	//
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new Gson();
		JsonObject sJson = gson.fromJson(request.getParameter("schedule"),JsonObject.class);
		JsonArray sJarr = sJson.get("classSchedule").getAsJsonArray();
		List<ScheduleVO> slist = new ArrayList<ScheduleVO>();
		for(int i=0;i<sJarr.size();i++) {
			ScheduleVO svo = new Gson().fromJson(sJarr.get(i),ScheduleVO.class);
			slist.add(svo);
		}
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("takeit-userid");
		
		//스케쥴 중복 체크
		int result = 0;
		ScheduleDAO sdao = new ScheduleDAO();
		for(int i=0;i<slist.size();i++){
			Date stime = slist.get(i).getStartTime();
			Date etime = slist.get(i).getEndTime();
			result = sdao.selectScheduleOverlap(userId,stime,etime);
			if(result>0){
				//중복 시 해당 마이페이지>수강/강의 내역으로 이동
				response.getWriter().println("OVERLAP");
				return;
			}
		}
		
		JsonObject cJson = gson.fromJson(request.getParameter("class"),JsonObject.class);
		
		//등록 조건 충족
		AssignmentVO avo = new AssignmentVO();
		int classid = cJson.get("classid").getAsInt();
		avo.setClassId(classid);
		avo.setUserId(userId);
		Date startdate = sdao.selectMinSchedule(classid);
		if(startdate==null) {
			//시작일 조회 실패
		}
		
		avo.setStartDate(startdate);
		Date enddate = sdao.selectMaxSchedule(classid);
		if(enddate==null) {
			//종료일 조회 실패
		}
		avo.setEndDate(enddate);
		AssignmentDAO adao = new AssignmentDAO();
		result = adao.insertAssignment(avo);
		if(result!=1) {
			//등록 실패
		}
		//등록 완료
	}

}
