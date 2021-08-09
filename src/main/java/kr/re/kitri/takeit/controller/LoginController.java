package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	String code = request.getParameter("code");

	RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
	rd.forward(request, response);
    }

}
