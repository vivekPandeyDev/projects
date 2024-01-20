package controller;

import java.io.File;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.StudentDao;
import dao.StudentDaoImpl;
import utility.CustomerException;


@WebServlet("/deleteStudent")
public class DeleteStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(DeleteStudent.class);

	public DeleteStudent() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			StudentDao studentDao = new StudentDaoImpl();
			String fileNameString = studentDao.getStudent(id).getImageName();
			if (!studentDao.removeStudent(id)) {
				throw new CustomerException("cannot delete the customer!!!");
			}
			String pathString = request.getRealPath("/") + "img" + File.separator;
			File file = new File(pathString + fileNameString);
			file.delete();
			response.sendRedirect("/crud/");
		} catch (Exception e) {
			logger.error("cannot delete customer {}", e.getMessage());
			throw new CustomerException(e.getMessage());
		}

	}

}
