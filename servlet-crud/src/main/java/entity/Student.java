package entity;

import java.time.LocalDate;
import java.util.Objects;

import utility.Qualification;

public class Student {
	private int studentId;
	private String studentName;
	private LocalDate studentDateOfBirth;
	private Qualification studentQualification;
	private Address studentAddress;
	private String gender;
	private String studentEmail;
	private String imageName;

	public Student() {
		super();

	}

	public Student(String studentName, LocalDate studentDateOfBirth, Qualification studentQualification,
			Address studentAddress, String gender, String studentEmail, String imageName) {
		super();
		this.studentName = studentName;
		this.studentDateOfBirth = studentDateOfBirth;
		this.studentQualification = studentQualification;
		this.studentAddress = studentAddress;
		this.gender = gender;
		this.studentEmail = studentEmail;
		this.imageName = imageName;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getStudentEmail() {
		return studentEmail;
	}

	public void setStudentEmail(String email) {
		this.studentEmail = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Address getStudentAddress() {
		return studentAddress;
	}

	public void setStudentAddress(Address studentAddress) {
		this.studentAddress = studentAddress;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public LocalDate getStudentDateOfBirth() {
		return studentDateOfBirth;
	}

	public void setStudentDateOfBirth(LocalDate studenDateOfBirth) {
		this.studentDateOfBirth = studenDateOfBirth;
	}

	public Qualification getStudentQualification() {
		return studentQualification;
	}

	public void setStudentQualification(Qualification studentQualification) {
		this.studentQualification = studentQualification;
	}

	@Override
	public int hashCode() {
		return Objects.hash(studentId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return studentId == other.studentId;
	}

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", studentName=" + studentName + ", studentDateOfBirth="
				+ studentDateOfBirth + ", studentQualification=" + studentQualification + ", studentAddress="
				+ studentAddress + ", gender=" + gender + ", studentEmail=" + studentEmail + ", imageName=" + imageName
				+ "]";
	}

}
