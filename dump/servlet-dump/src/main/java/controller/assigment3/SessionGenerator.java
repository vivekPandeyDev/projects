package controller.assigment3;

import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet(description = "This creates user session", urlPatterns = { "/sessionGenerator" })
public class SessionGenerator extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(SessionGenerator.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SessionGenerator() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		session.setAttribute("count",0 );
		try (PrintWriter out = resp.getWriter()) {
				out.write("<h1> Enter Name</h1>");
				out.write("<form  method=\"get\" action=\"counter\">");
				out.write("<input type=\"text\" name=\"user\"><br>");
				out.write("<input type=\"submit\"><br>");
				out.write("</form>");
		} catch (Exception e) {
			logger.error("Exception: {}", e.getMessage());
		}
	}

}
