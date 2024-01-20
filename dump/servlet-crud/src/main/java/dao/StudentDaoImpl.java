package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import connection.MyConnection;
import entity.Student;
import utility.Qualification;

public class StudentDaoImpl implements StudentDao {

	private static final Logger logger = LogManager.getLogger(StudentDaoImpl.class);

	@Override
	public boolean addStudent(Student student) {
		String query = "insert into vivek_student(studentName,dateofBirth,qualification,gender,studentEmail,imageName) values(?,?,?,?,?,?)";
		try (Connection connection = MyConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, student.getStudentName());
			preparedStatement.setDate(2, Date.valueOf(student.getStudentDateOfBirth()));
			preparedStatement.setString(3, student.getStudentQualification().toString());
			preparedStatement.setString(4, student.getGender());
			preparedStatement.setString(5, student.getStudentEmail());
			preparedStatement.setString(6, student.getImageName());
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			logger.error("cannot add student, {}", e.getMessage());
			return false;
		}

		return true;
	}

	@Override
	public boolean removeStudent(int studentId) {
		String query = "delete from  vivek_student where studentId=?";
		try (Connection connection = MyConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, studentId);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			logger.error("cannot remove student, {}", e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public boolean updateStudent(int studentId, Student student) {
		String query = "update  vivek_student set studentName=?, dateofBirth=?, qualification=?, gender=?, studentEmail=?, imageName=? where studentId=?";
		try (Connection connection = MyConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			preparedStatement.setString(1, student.getStudentName());
			preparedStatement.setDate(2, Date.valueOf(student.getStudentDateOfBirth()));
			preparedStatement.setString(3, student.getStudentQualification().toString());
			preparedStatement.setString(4, student.getGender());
			preparedStatement.setString(5, student.getStudentEmail());
			preparedStatement.setString(6, student.getImageName());
			preparedStatement.setInt(7, studentId);
			preparedStatement.executeUpdate();

		} catch (Exception e) {
			logger.error("cannot update student, {}", e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public Set<Student> getStudents() {
		Set<Student> students = new HashSet<>();
		String query = "select studentId,studentName,dateofBirth,qualification,gender,studentEmail,imageName from vivek_student";
		try (Connection connection = MyConnection.getConnection();
				Statement statement = connection.createStatement();) {

			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				Student student = new Student();
				int id = resultSet.getInt(1);
				String name = resultSet.getString(2);
				LocalDate date = resultSet.getDate(3).toLocalDate();
				Qualification qualification = Qualification.valueOf(resultSet.getString(4));
				String gender = resultSet.getString(5);
				/*
				 * Blob blob = resultSet.getBlob(6); int blobLength = (int) blob.length();
				 * byte[] image = blob.getBytes(1, blobLength);
				 */
				String email = resultSet.getString(6);
				String imageName = resultSet.getString(7);
				student.setStudentId(id);
				student.setStudentName(name);
				student.setStudentDateOfBirth(date);
				student.setStudentQualification(qualification);
				student.setGender(gender);
				student.setStudentEmail(email);
				student.setImageName(imageName);
				AddressDao addressDao = new AddressDaoImpl();
				student.setStudentAddress(addressDao.getAddresses(id));
				students.add(student);

			}
		} catch (Exception e) {
			logger.error("cannot get all student: {}", e.getMessage());
		}

		return students;
	}

	@Override
	public int getStudentId(String email) {
		int id = -1;
		String query = "select studentId from vivek_student where studentEmail=?";
		try (Connection connection = MyConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, email);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				id = resultSet.getInt(1);
			}
		} catch (Exception e) {
			logger.error("cannot get student id {}", e.getMessage());
		}

		return id;
	}

	@Override
	public Student getStudent(int studentId) {
		Student student = null;
		String query = "select studentId,studentName,dateofBirth,qualification,gender,studentEmail,imageName from vivek_student where studentId="+studentId;
		try (Connection connection = MyConnection.getConnection();
				Statement statement = connection.createStatement();) {

			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				int id = resultSet.getInt(1);
				String name = resultSet.getString(2);
				LocalDate date = resultSet.getDate(3).toLocalDate();
				Qualification qualification = Qualification.valueOf(resultSet.getString(4));
				String gender = resultSet.getString(5);
				String email = resultSet.getString(6);
				String imageName = resultSet.getString(7);
				student = new Student();
				student.setStudentId(id);
				student.setStudentName(name);
				student.setStudentDateOfBirth(date);
				student.setStudentQualification(qualification);
				student.setGender(gender);
				student.setStudentEmail(email);
				student.setImageName(imageName);
				AddressDao addressDao = new AddressDaoImpl();
				student.setStudentAddress(addressDao.getAddresses(id));
			
			}
		} catch (Exception e) {
			logger.error("cannot get all student: {}", e.getMessage());
		}

		return student;
	}

}
