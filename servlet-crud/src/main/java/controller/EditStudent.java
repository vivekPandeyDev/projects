package controller;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.AddressDao;
import dao.AddressDaoImpl;
import dao.StudentDao;
import dao.StudentDaoImpl;
import entity.Address;
import entity.Student;
import service.AddressService;
import service.StudentService;
import utility.CustomerException;
import utility.IoUtitlity;

@WebServlet("/editStudent")
@MultipartConfig
public class EditStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EditStudent() {
		super();

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		StudentDao studentDao = new StudentDaoImpl();
		req.setAttribute("student", studentDao.getStudent(id));
		req.getRequestDispatcher("jsp/editStudent.jsp").forward(req, resp);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// image upload
		Part part = request.getPart("student_photo");
		String pathString = request.getRealPath("/") + "img" + File.separator;
		int id = Integer.parseInt(request.getParameter("id"));
		if (Pattern.matches("image", part.getContentType().split("/")[0])) {
			Student student = StudentService.getStudent(request);
			Address address = AddressService.getAddress(request);
			student.setStudentAddress(address);
			String fileName = student.getStudentEmail() + "." + part.getContentType().split("/")[1];
			student.setImageName(fileName);
			StudentDao studentDao = new StudentDaoImpl();
			AddressDao addressDao = new AddressDaoImpl();
			String oldFileName = studentDao.getStudent(id).getImageName();
			if (studentDao.updateStudent(id, student)) {
				if (addressDao.updateAdress(id, address)) {
					File file = new File(pathString + oldFileName);
					file.delete();
					IoUtitlity.uploadImage(part, pathString + fileName);

					response.sendRedirect("/crud/");
				} else {
					throw new CustomerException("cannot update address to database!!!");
				}

			} else {
				throw new CustomerException("Cannot update student to database");
			}
			return;
		}
		throw new CustomerException("Please Enter Image only!!!!");
	}

}
