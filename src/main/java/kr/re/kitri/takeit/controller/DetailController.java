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

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import dao.ClassDAO;
import dao.ReviewDAO;
import vo.ClassVO;
import vo.ReviewVO;

@WebServlet("/detail/*")
public class DetailController extends HttpServlet {
	// 클래스 상세정보 받아오기

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI().substring(19);
		// System.out.println(requestURI);

		if (requestURI.matches("[+-]?\\d*(\\.\\d+)?")) {
			int classid = Integer.parseInt(requestURI);
			// 주소값이 숫자일 경우만
			ReviewDAO rdao = new ReviewDAO();
			ReviewVO rvo = rdao.getRecommandCountAndScore(classid);
			List<ReviewVO> rlist = rdao.getRecentRecommands(classid);
			// System.out.println(rvo.getTotalRecommendNum() + "/" +
			// rvo.getAvgScore());
			for (ReviewVO c : rlist) {
				System.out.println(c.toString());
			}

			ClassDAO cdao = new ClassDAO();
			ClassVO cvo = cdao.getClassDetail(classid);
			// System.out.println(cvo.toString());

			// 보낼 데이터 json 객체에 취합 / 총 후기 개수, 총 후기 평점, 후기내용리스트(2개), 클래스 정보
			JsonObject parseDetailJson = new JsonObject();
			// 총 후기 개수
			parseDetailJson.addProperty("recommend_num",
					rvo.getTotalRecommendNum());
			// 총 후기 평점
			parseDetailJson.addProperty("recommend_score", rvo.getAvgScore());
			// 후기내용리스트(2개)
			JsonArray reviewJsonArr = new JsonArray();
			for (ReviewVO c : rlist) {
				JsonObject reviewJson = new JsonObject();
				reviewJson.addProperty("username", c.getUserName());
				reviewJson.addProperty("reviewdate",
						String.valueOf(c.getReviewDate()));
				reviewJson.addProperty("content", c.getContent());
				reviewJsonArr.add(reviewJson);
			}
			parseDetailJson.addProperty("reviewlist",
					String.valueOf(reviewJsonArr));
			// 클래스 정보
			JsonObject classdetailJson = new JsonObject();
			classdetailJson.addProperty("classname", cvo.getClassName());
			classdetailJson.addProperty("creatername", cvo.getCreater());
			classdetailJson.addProperty("introduce", cvo.getIntroduce());
			classdetailJson.addProperty("period", cvo.getPeriod());
			classdetailJson.addProperty("content_num", cvo.getContent_num());
			classdetailJson.addProperty("detail", cvo.getDetail());
			classdetailJson.addProperty("chapter", cvo.getChapter());
			classdetailJson.addProperty("creater_info", cvo.getCreater_info());
			classdetailJson.addProperty("address", cvo.getAddress());

			parseDetailJson.addProperty("classinfo",
					String.valueOf(classdetailJson));

			HttpSession session = request.getSession();
			session.setAttribute("detailclass", parseDetailJson.toString());
			RequestDispatcher rd = request
					.getRequestDispatcher("/WEB-INF/jsp/class-detail.jsp");
			rd.forward(request, response);
		} else {
			// 경로에 숫자가 아닐경우 404처리해야함
		}
	}

}
