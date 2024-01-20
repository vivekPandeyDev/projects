package com.webservices.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.webservices.enums.EnumClass;

@Entity
@DiscriminatorValue("homeloan_vehicle_loan")
@Table(name = "vivek_jpa_homeloan")
public class HomeLoan extends LoanProduct {
	private double marketValue;
	private EnumClass.PropertyType propertyType;
	private EnumClass.NatureOfProperty natureOfProperty;
	private EnumClass.PropertyPurpose propertyPurpose;
	private EnumClass.PropertyOwnership propertyOwnership;
	private double builtUpArea;
	private double carpetArea;
	private int propertyAge;

	public HomeLoan() {
		super();
	}

	private HomeLoan(HomeLoanBuilder builder) {
		super(builder.loanProductBuilder);
		this.marketValue = builder.marketValue;
		this.propertyType = builder.propertyType;
		this.natureOfProperty = builder.natureOfProperty;
		this.propertyPurpose = builder.propertyPurpose;
		this.propertyOwnership = builder.propertyOwnership;
		this.builtUpArea = builder.builtUpArea;
		this.carpetArea = builder.carpetArea;
		this.propertyAge = builder.propertyAge;
		this.setLtv(ltvCalculationAsPerCollateralType(builder.askedLoanAmount));
	}

	public static class HomeLoanBuilder {
		// mandatory
		private final double marketValue;
		private final double askedLoanAmount;
		private final LoanProductBuilder loanProductBuilder;
		// optional
		private EnumClass.PropertyType propertyType;
		private EnumClass.NatureOfProperty natureOfProperty;
		private EnumClass.PropertyPurpose propertyPurpose;
		private EnumClass.PropertyOwnership propertyOwnership;
		private double builtUpArea;
		private double carpetArea;
		private int propertyAge;

		public HomeLoanBuilder(LoanProductBuilder builder, double marketValue,
				double askedLoanAmount) {
			this.loanProductBuilder = builder;
			this.marketValue = marketValue;
			this.askedLoanAmount = askedLoanAmount;
		}

		public HomeLoanBuilder setPropertyType(
				EnumClass.PropertyType propertyType) {
			this.propertyType = propertyType;
			return this;
		}

		public HomeLoanBuilder setNatureOfProperty(
				EnumClass.NatureOfProperty natureOfProperty) {
			this.natureOfProperty = natureOfProperty;
			return this;
		}

		public HomeLoanBuilder setPropertyPurpose(
				EnumClass.PropertyPurpose propertyPurpose) {
			this.propertyPurpose = propertyPurpose;
			return this;
		}

		public HomeLoanBuilder setPropertyOwnership(
				EnumClass.PropertyOwnership propertyOwnership) {
			this.propertyOwnership = propertyOwnership;
			return this;
		}

		public HomeLoanBuilder setBuiltUpArea(double builtUpArea) {
			this.builtUpArea = builtUpArea;
			return this;
		}

		public HomeLoanBuilder setCarpetArea(double carpetArea) {
			this.carpetArea = carpetArea;
			return this;
		}

		public HomeLoanBuilder setPropertyAge(int propertyAge) {
			this.propertyAge = propertyAge;
			return this;
		}

		public HomeLoan build() {
			return new HomeLoan(this);
		}
	}

	public double getMarketValue() {
		return marketValue;
	}

	public EnumClass.PropertyType getPropertyType() {
		return propertyType;
	}

	public EnumClass.NatureOfProperty getNatureOfProperty() {
		return natureOfProperty;
	}

	public EnumClass.PropertyPurpose getPropertyPurpose() {
		return propertyPurpose;
	}

	public EnumClass.PropertyOwnership getPropertyOwnership() {
		return propertyOwnership;
	}

	public double getBuiltUpArea() {
		return builtUpArea;
	}

	public double getCarpetArea() {
		return carpetArea;
	}

	public int getPropertyAge() {
		return propertyAge;
	}

	public HomeLoan setMarketValue(double marketValue) {
		this.marketValue = marketValue;
		return this;
	}

	public HomeLoan setPropertyType(EnumClass.PropertyType propertyType) {
		this.propertyType = propertyType;
		return this;
	}

	public HomeLoan setNatureOfProperty(
			EnumClass.NatureOfProperty natureOfProperty) {
		this.natureOfProperty = natureOfProperty;
		return this;
	}

	public HomeLoan setPropertyPurpose(
			EnumClass.PropertyPurpose propertyPurpose) {
		this.propertyPurpose = propertyPurpose;
		return this;
	}

	public HomeLoan setPropertyOwnership(
			EnumClass.PropertyOwnership propertyOwnership) {
		this.propertyOwnership = propertyOwnership;
		return this;
	}

	public HomeLoan setBuiltUpArea(double builtUpArea) {
		this.builtUpArea = builtUpArea;
		return this;
	}

	public HomeLoan setCarpetArea(double carpetArea) {
		this.carpetArea = carpetArea;
		return this;
	}

	public HomeLoan setPropertyAge(int propertyAge) {
		this.propertyAge = propertyAge;
		return this;
	}

	@Override
	public double ltvCalculationAsPerCollateralType(double askedLoanAmount) {
		return askedLoanAmount / marketValue;
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
		return "HomeLoan{" + "LoanProduct= " + super.toString() + "marketValue="
				+ marketValue + ", propertyType=" + propertyType
				+ ", natureOfProperty=" + natureOfProperty
				+ ", propertyPurpose=" + propertyPurpose
				+ ", propertyOwnership=" + propertyOwnership + ", builtUpArea="
				+ builtUpArea + ", carpetArea=" + carpetArea + ", propertyAge="
				+ propertyAge + '}';
	}
}
