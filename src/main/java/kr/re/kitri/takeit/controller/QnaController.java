package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.QnaDAO;
import vo.QnaVO;

@WebServlet("/qna-insert")
public class QnaController extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// String userid = request.getParameter("userid"); //form에 대한
		// 값(textarea) 서브밋으로 가져오기
		// String qnadate = request.getParameter("qnadate");
		// String question = request.getParameter("question");
		// String answer = request.getParameter("answer");
		//
		//
		// HttpSession session = request.getSession();
		// String id = (String)session.getAttribute("userid");
		//
		// QnaVO qvo = new QnaVO();
		// qvo.setUserid(userid);
		// qvo.setQnadate(Date.valueOf(qnadate));
		// qvo.setQuestion(question);
		// qvo.setAnswer(answer);
		//
		// QnaDAO mdao = new QnaDAO();
		// mdao.qnaRegist(qvo);
		//
		// response.sendRedirect(request.getContextPath()+"/index");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String qna = request.getParameter("qna"); // textarea name 값이다.
		String qnaTilte = request.getParameter("qna_title");
		System.out.print(qna);
		System.out.print(qnaTilte);
		
		QnaVO qvo = new QnaVO();
		qvo.setUserId("admin");
		
		qvo.setQuestion(qna);
		qvo.setQnaTitle(qnaTilte);
		
		QnaDAO qdao = new QnaDAO();
		qdao.qna(qvo);
		
//		response.sendRedirect(request.getContextPath() + "/qna.jsp");
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/qna.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
