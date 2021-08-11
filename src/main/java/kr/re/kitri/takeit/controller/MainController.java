package controller;

import java.io.IOException;
import java.io.PrintWriter;
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

import dao.ClassDAO;
import pagination.Pagination;
import vo.ClassJson;
import vo.ClassVO;

@WebServlet("/main")
public class MainController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String category = request.getParameter("category");
		String range = request.getParameter("range");
		
		if(category != null) {
			session.setAttribute("category", category);			
		}
		
		System.out.println(category);
		System.out.println(range);
		ClassDAO cdao = new ClassDAO();

		String curPage = request.getParameter("curpage");
		if(curPage == null){
			curPage = "1";
		}
		int curPageInt = Integer.parseInt(curPage);
		int totalContent = cdao.selectClassCnt("O");
		
		Pagination pagination = new Pagination(curPageInt, totalContent, 5);
		
		//한 페이지 내에 보여줘야 하는 게시물의 첫 번째 rownum
		int start = (curPageInt*pagination.getContentCnt()) - (pagination.getContentCnt() - 1);
		
		//한 페이지 내에 보여줘야 하는 게시물의 마지막 rownum
		int end = curPageInt*pagination.getContentCnt();
		List<ClassVO> clist = cdao.selectClassPage(category, range, start, end);
		
		request.setAttribute("clist", clist);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
	}

}
