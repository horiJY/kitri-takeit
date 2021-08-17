package controller;

import java.io.IOException;
import java.util.ArrayList;
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
			String stime = slist.get(i).getStartTime();
			String etime = slist.get(i).getEndTime();
			result = sdao.selectScheduleOverlap(userId,stime,etime);
			if(result>0){
				//중복 시 해당 마이페이지>수강/강의 내역으로 이동
				response.getWriter().println("OVERLAP");
				return;
			}
		}
		
		ClassVO cvo = gson.fromJson(request.getParameter("class"),ClassVO.class);
		
		//포인트 잔액 조회
		UserDAO udao = new UserDAO();
		int point = udao.selectPoint(userId);
		if(point < 0) {
			//포인트 조회 실패
			
		}else if (point < cvo.getPrice()) {
			//포인트 부족시 결제로 이동
		}

		//등록 조건 충족
		AssignmentVO avo = new AssignmentVO();
		avo.setClassId(cvo.getClassId());
	}

}
