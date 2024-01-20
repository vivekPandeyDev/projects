package controller.assigment3;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class Login extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		Question 2
		HttpSession session = req.getSession();
		session.setAttribute("msg", "session created by login");
		try {
			resp.sendRedirect("page");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
