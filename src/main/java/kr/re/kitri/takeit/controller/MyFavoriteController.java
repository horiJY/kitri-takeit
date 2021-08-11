package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
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
import json.FavoriteClassJson;
import vo.ClassVO;


@WebServlet("/myfavorite")
public class MyFavoriteController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		if(id==null) {
			id="test";
//			response.sendRedirect(request.getContextPath()+"/login");
//		} else {
			ClassDAO cdao = new ClassDAO();
			System.out.println(id);
			List<ClassVO> clist = cdao.selectFavoriteClass(id);
			List<FavoriteClassJson> fcjlist = new ArrayList<FavoriteClassJson>();
			System.out.println(clist);
			if(clist==null) {
				FavoriteClassJson fcj = new FavoriteClassJson();
				fcj.setClassName("테스트클래스");
				fcj.setCreater("테스트");
				fcj.setFavorite(100);
				fcj.setOpenDate(null);
				
				fcjlist.add(fcj);
			} else {
				for (ClassVO cvo: clist) {
				FavoriteClassJson fcj = new FavoriteClassJson();
				fcj.setClassName(cvo.getClassName());
				fcj.setCreater(cvo.getCreater());
				fcj.setFavorite(cvo.getFavorite());
				fcj.setOpenDate(cvo.getOpenDate());
				
				fcjlist.add(fcj);
				}
			}
			
			
			Gson gson = new Gson();
			String json = gson.toJson(fcjlist);
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			System.out.println(json);
			out.print(json);
//			request.setAttribute("fcjson", json);
//			
//			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/mypage.jsp");
//			rd.forward(request, response);
		}
	}

}
