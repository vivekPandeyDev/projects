package entity;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;


@Component
public class Student {
    private static final Logger logger = LogManager.getLogger(Student.class);

    private int studentId;
    private String studentName;
    private Address address;

    public Student(int studentId, String studentName, Address address) {
	super();
	this.studentId = studentId;
	this.studentName = studentName;
	this.address = address;
    }

    @PostConstruct
    public void myInit() {
	logger.info("init method called!!!!");
    }

    @PreDestroy
    public void destroy() {
	logger.info("destroy method called!!!!");

    }

    public Student() {
	super();
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

    public Address getAddress() {
	return address;
    }

    public void setAddress(Address address) {
	logger.info("setter method called!!!!");
	this.address = address;
    }

    @Override
    public String toString() {
	return "Student [studentId=" + studentId + ", studentName=" + studentName + ", address=" + address + "]";
    }

}
