package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ClassDAO;
import dao.ReviewDAO;
import vo.ClassVO;
import vo.ReviewVO;

@WebServlet("/detail")
public class DetailController extends HttpServlet {
//클래스 상세정보 받아오기

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	int classid = 1;

	ReviewDAO rdao = new ReviewDAO();
	ReviewVO rvo = rdao.getRecommandCountAndScore(classid);
	List<ReviewVO> rlist = rdao.getRecentRecommands(classid);
	System.out.println(rvo.getTotalRecommendNum() + "/" + rvo.getAvgScore());
	for (ReviewVO c : rlist) {
	    System.out.println(c.toString());
	}

	ClassDAO cdao = new ClassDAO();
	ClassVO cvo = cdao.getClassDetail(classid);
	System.out.println(cvo.toString());

//	RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/class-detail.jsp");
//	rd.forward(request, response);
    }

}
