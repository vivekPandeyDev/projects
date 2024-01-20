package controller.assigment3;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Question3
 */
@WebServlet("/question3")
public class Question3 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("user", request.getParameter("user"));
		String checkedString = request.getParameter("signIn");

		if (checkedString != null) {
			Cookie cookie = new Cookie("user", request.getParameter("user"));
			cookie.setMaxAge(2 * 60);
			response.addCookie(cookie);
		}
		try {
			response.sendRedirect("jsp/privateResource.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
