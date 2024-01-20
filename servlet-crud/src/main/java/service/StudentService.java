package service;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;

import entity.Student;
import utility.CustomerException;
import utility.Qualification;

public class StudentService {

	/*
	 * public static byte[] getStudentImage(HttpServletRequest request) { byte[]
	 * image = null; try { Part part = request.getPart("student_photo"); InputStream
	 * stream = part.getInputStream(); image =
	 * stream.readNBytes(stream.available()); } catch (Exception e) { throw new
	 * CustomerException("cannot get Image, " + e.getMessage()); } return image; }
	 */
	
	public static Student getStudent(HttpServletRequest request) {
		Student student = null;
		try {
			String name = request.getParameter("student_name");
			String email = request.getParameter("student_email");
			LocalDate date = LocalDate.parse(request.getParameter("student_date_of_birth"));
			Qualification qualification = Qualification.valueOf(request.getParameter("student_qualification"));
			String gender = request.getParameter("student_gender");
			student = new Student( name, date, qualification, null, gender, email, name);
		} catch (Exception e) {
			throw new CustomerException("cannot get student " + e.getMessage());
		}
		
		return student;
		
	}

}
