package controller.assigment3;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/displayCookies")
public class DisplayCookies extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		try {
			PrintWriter printWriter = resp.getWriter();
			Cookie[] cookies = req.getCookies();
			if (cookies == null) {
				printWriter.println("No Cookies");
			} else {
				printWriter.println("<table border=\"3\">");
				for (Cookie cookie : cookies) {
					printWriter.println("<tr>");
					printWriter.println("<td>");
					printWriter.println(cookie.getValue());
					printWriter.println("</td>");
					printWriter.println("<td>");
					printWriter.println(cookie.getName());
					printWriter.println("</td>");
					printWriter.println("</tr>");
				}
				printWriter.println("</table>");
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
