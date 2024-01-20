package com.webservices.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.webservices.enums.EnumClass;


@Entity
@DiscriminatorValue("consumer_vehicle_loan")
@Table(name = "vivek_jpa_consumer_vehicle_loan")
public class ConsumerVehicleLoan extends LoanProduct {
	@Enumerated(EnumType.STRING)
	private EnumClass.AssetCategory assetCategory;
	@Enumerated(EnumType.STRING)
	private EnumClass.AssetVariant assetVariant;
	private String assetModel;
	private String manufacturer;
	private int yearOfManufacture;
	private double assetCost;
	private double downPayment;
	
	
	
	

	public ConsumerVehicleLoan setAssetCategory(EnumClass.AssetCategory assetCategory) {
		this.assetCategory = assetCategory;
		return this;
	}

	public ConsumerVehicleLoan setAssetVariant(EnumClass.AssetVariant assetVariant) {
		this.assetVariant = assetVariant;
		return this;
	}

	public ConsumerVehicleLoan setAssetModel(String assetModel) {
		this.assetModel = assetModel;
		return this;
	}

	public ConsumerVehicleLoan setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
		return this;
	}

	public ConsumerVehicleLoan setYearOfManufacture(int yearOfManufacture) {
		this.yearOfManufacture = yearOfManufacture;
		return this;
	}

	public ConsumerVehicleLoan setAssetCost(double assetCost) {
		this.assetCost = assetCost;
		return this;
	}

	public ConsumerVehicleLoan setDownPayment(double downPayment) {
		this.downPayment = downPayment;
		return this;
	}


	public ConsumerVehicleLoan() {
		super();
	}

	public ConsumerVehicleLoan(ConsumerVehicleLoanBuilder builder) {
		super(builder.loanProductBuilder);
		this.assetCost = builder.assetCost;
		this.assetCategory = builder.assetCategory;
		this.assetVariant = builder.assetVariant;
		this.assetModel = builder.assetModel;
		this.manufacturer = builder.manufacturer;
		this.yearOfManufacture = builder.yearOfManufacture;
		this.downPayment = builder.downPayment;
		this.setLtv(ltvCalculationAsPerCollateralType(builder.askedLoanAmount));
	}

	public static class ConsumerVehicleLoanBuilder {
		// mandatory
		private final double askedLoanAmount;
		private final double assetCost;
		private final LoanProductBuilder loanProductBuilder;

		// optional
		private EnumClass.AssetCategory assetCategory;
		private EnumClass.AssetVariant assetVariant;
		private String assetModel;
		private String manufacturer;
		private int yearOfManufacture;
		private double downPayment;

		public ConsumerVehicleLoanBuilder(LoanProductBuilder loanProductBuilder,
				double assetCost, double askedLoanAmount) {
			this.loanProductBuilder = loanProductBuilder;
			this.assetCost = assetCost;
			this.askedLoanAmount = askedLoanAmount;
		}

		public ConsumerVehicleLoanBuilder setAssetCategory(
				EnumClass.AssetCategory assetCategory) {
			this.assetCategory = assetCategory;
			return this;
		}

		public ConsumerVehicleLoanBuilder setAssetVariant(
				EnumClass.AssetVariant assetVariant) {
			this.assetVariant = assetVariant;
			return this;
		}

		public ConsumerVehicleLoanBuilder setAssetModel(String assetModel) {
			this.assetModel = assetModel;
			return this;
		}

		public ConsumerVehicleLoanBuilder setManufacturer(String manufacturer) {
			this.manufacturer = manufacturer;
			return this;
		}

		public ConsumerVehicleLoanBuilder setYearOfManufacture(
				int yearOfManufacture) {
			this.yearOfManufacture = yearOfManufacture;
			return this;
		}

		public ConsumerVehicleLoanBuilder setDownPayment(double downPayment) {
			this.downPayment = downPayment;
			return this;
		}

		public ConsumerVehicleLoan build() {
			return new ConsumerVehicleLoan(this);
		}
	}

	public EnumClass.AssetCategory getAssetCategory() {
		return assetCategory;
	}

	public EnumClass.AssetVariant getAssetVariant() {
		return assetVariant;
	}

	public String getAssetModel() {
		return assetModel;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public int getYearOfManufacture() {
		return yearOfManufacture;
	}

	public double getAssetCost() {
		return assetCost;
	}

	public double getDownPayment() {
		return downPayment;
	}

	@Override
	public double ltvCalculationAsPerCollateralType(double askedLoanAmount) {
		return askedLoanAmount / assetCost;
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
		return "ConsumerVehicleLoan{" + "LoanProduct=" + super.toString()
				+ "assetCategory=" + assetCategory + ", assetVariant="
				+ assetVariant + ", assetModel='" + assetModel + '\''
				+ ", manufacturer='" + manufacturer + '\''
				+ ", yearOfManufacture=" + yearOfManufacture + ", assetCost="
				+ assetCost + ", downPayment=" + downPayment + '}';
	}
}
