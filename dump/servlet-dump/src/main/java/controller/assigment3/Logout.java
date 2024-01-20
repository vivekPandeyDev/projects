package controller.assigment3;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class Logout extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		Question 2
		HttpSession session = req.getSession(false);
		try (PrintWriter out = resp.getWriter()) {
			if (session != null) {
				session.invalidate();
				out.write("Logout successfully!!!");
			} else {
				out.write("Cannot Logout, out no session!!!!");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
