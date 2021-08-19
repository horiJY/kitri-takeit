package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutController
 */
@WebServlet("/logout")
public class LogoutController extends HttpServlet {
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HttpSession session = request.getSession();
    // attribute 할때 - 말고 _로 할 것 : JS에서 마이너스 연산으로 인식할 수 있음
    session.setAttribute("takeit_userid", null);
    session.setAttribute("takeit_username", null);
    session.setAttribute("takeit_userthumnail", null);

    response.sendRedirect("http://localhost:8080/takeit_prj/main");
  }


}
