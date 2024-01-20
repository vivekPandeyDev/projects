package com.webservices.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.webservices.enums.EnumClass;

@Entity
@DiscriminatorValue("education_vehicle_loan")
@Table(name = "vivek_jpa_educationloan")
public class EducationLoan extends LoanProduct {
	private String courseName;
	private String collegeName;
	private EnumClass.CourseType courseType;
	private EnumClass.DegreeType degreeType;
	private EnumClass.EducationStream educationStream;
	private double totalFees;
	public EducationLoan() {
		super();
	}



	private EducationLoan(EducationLoanBuilder builder) {
		super(builder.loanProductBuilder);
		this.courseName = builder.courseName;
		this.collegeName = builder.collegeName;
		this.courseType = builder.courseType;
		this.degreeType = builder.degreeType;
		this.educationStream = builder.educationStream;
		this.totalFees = builder.totalFees;
		this.setLtv(ltvCalculationAsPerCollateralType(builder.askedLoanAmount));
	}

	public static class EducationLoanBuilder {
		// mandatory
		private final double askedLoanAmount;
		private final double totalFees;
		private final LoanProductBuilder loanProductBuilder;
		// optional
		private String courseName;
		private String collegeName;
		private EnumClass.CourseType courseType;
		private EnumClass.DegreeType degreeType;
		private EnumClass.EducationStream educationStream;

		public EducationLoanBuilder(LoanProductBuilder loanProductBuilder,
				double totalFees, double askedLoanAmount) {
			this.loanProductBuilder = loanProductBuilder;
			this.totalFees = totalFees;
			this.askedLoanAmount = askedLoanAmount;
		}

		public EducationLoanBuilder setCourseName(String courseName) {
			this.courseName = courseName;
			return this;
		}

		public EducationLoanBuilder setCollegeName(String collegeName) {
			this.collegeName = collegeName;
			return this;
		}

		public EducationLoanBuilder setCourseType(
				EnumClass.CourseType courseType) {
			this.courseType = courseType;
			return this;
		}

		public EducationLoanBuilder setDegreeType(
				EnumClass.DegreeType degreeType) {
			this.degreeType = degreeType;
			return this;
		}

		public EducationLoanBuilder setEducationStream(
				EnumClass.EducationStream educationStream) {
			this.educationStream = educationStream;
			return this;
		}

		public EducationLoan build() {
			return new EducationLoan(this);
		}
	}

	public String getCourseName() {
		return courseName;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public EnumClass.CourseType getCourseType() {
		return courseType;
	}

	public EnumClass.DegreeType getDegreeType() {
		return degreeType;
	}

	public EnumClass.EducationStream getEducationStream() {
		return educationStream;
	}

	public double getTotalFees() {
		return totalFees;
	}

	public EducationLoan setCourseName(String courseName) {
		this.courseName = courseName;
		return this;
	}

	public EducationLoan setCollegeName(String collegeName) {
		this.collegeName = collegeName;
		return this;
	}

	public EducationLoan setCourseType(EnumClass.CourseType courseType) {
		this.courseType = courseType;
		return this;
	}

	public EducationLoan setDegreeType(EnumClass.DegreeType degreeType) {
		this.degreeType = degreeType;
		return this;
	}

	public EducationLoan setEducationStream(
			EnumClass.EducationStream educationStream) {
		this.educationStream = educationStream;
		return this;
	}

	public EducationLoan setTotalFees(double totalFees) {
		this.totalFees = totalFees;
		return this;
	}

	@Override
	public double ltvCalculationAsPerCollateralType(double askedLoanAmount) {
		return askedLoanAmount / totalFees;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj instanceof LoanProduct)
			return this.getLoanProductCode()
					.equals(((LoanProduct) obj).getLoanProductCode());
		return false;
	}

	@Override
	public String toString() {
		return "EducationLoan{" + "LoanProduct=" + super.toString()
				+ "courseName='" + courseName + '\'' + ", collegeName='"
				+ collegeName + '\'' + ", courseType=" + courseType
				+ ", degreeType=" + degreeType + ", educationStream="
				+ educationStream + ", totalFees=" + totalFees + '}';
	}
}
