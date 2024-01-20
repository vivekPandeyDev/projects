package controller;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.StudentDao;
import dao.StudentDaoImpl;
import entity.Student;

@WebServlet("/studentProfile")
public class ProfilePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(ProfilePage.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int id = Integer.parseInt(Objects.requireNonNull(request.getParameter("id")));
			StudentDao studentDao = new StudentDaoImpl();
			Student student =  studentDao.getStudent(id);
			request.setAttribute("student", student);
			request.getRequestDispatcher("/WEB-INF/view/profile.jsp").forward(request, response);
		} catch (Exception e) {
			logger.error("cannot generate profile: {}", e.getMessage());
		}
	}

}
