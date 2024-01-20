package controller.assigment3;

import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet("/counter")
public class Counter extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(Counter.class);

	public Counter() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession(false);
		resp.setContentType("text/html");
		try (PrintWriter out = resp.getWriter()) {
			if (session == null) {
				out.write("</h1>Session Expired!!!</h1>");
				out.write("<a href=\"sessionGenerator\">home</a>");
			} else {
				String user = req.getParameter("user");
				int count = (Integer) session.getAttribute("count");
				out.write("Welcome " + user + " no of times visited: " + count);
				session.setAttribute("count", count + 1);
			}
		} catch (Exception e) {
			logger.error("Exception: {}", e.getMessage());
		}
	}

}
