package com.webservices.entity;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
//@MappedSuperclass
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "loanProduct")
@Table(name = "vivek_jpa_loanproduct")
public abstract class LoanProduct {
	// mandatory
	@Id
	private String loanProductCode;
	private String loanProductName;
	private double roi;
	private double ltv; // to be calculated
	// optional
	private boolean assetBased;
	private String loanSecurityType;
	private double minTenure;
	private double maxTenure;
	private double minLoanAmount;
	private double maxLoanAmount;

	protected LoanProduct() {
		super();
	}

	protected LoanProduct(LoanProductBuilder builder) {
		this.loanProductCode = builder.loanProductCode;
		this.loanProductName = builder.loanProductName;
		this.roi = builder.roi;
		this.assetBased = builder.assetBased;
		this.loanSecurityType = builder.loanSecurityType;
		this.minTenure = builder.minTenure;
		this.maxTenure = builder.maxTenure;
		this.minLoanAmount = builder.minLoanAmount;
		this.maxLoanAmount = builder.maxLoanAmount;
	}

	public static class LoanProductBuilder {
		// mandatory
		private final String loanProductCode;
		private final String loanProductName;
		private final double roi;
		// optional
		private boolean assetBased;
		private String loanSecurityType;
		private double minTenure;
		private double maxTenure;
		private double minLoanAmount;
		private double maxLoanAmount;

		public LoanProductBuilder(String loanProductCode,
				String loanProductName, double roi) {
			this.loanProductCode = loanProductCode;
			this.loanProductName = loanProductName;
			this.roi = roi;
		}

		public LoanProductBuilder setAssetBased(boolean assetBased) {
			this.assetBased = assetBased;
			return this;
		}

		public LoanProductBuilder setLoanSecurityType(String loanSecurityType) {
			this.loanSecurityType = loanSecurityType;
			return this;
		}

		public LoanProductBuilder setMinTenure(double minTenure) {
			this.minTenure = minTenure;
			return this;
		}

		public LoanProductBuilder setMaxTenure(double maxTenure) {
			this.maxTenure = maxTenure;
			return this;
		}

		public LoanProductBuilder setMinLoanAmount(double minLoanAmount) {
			this.minLoanAmount = minLoanAmount;
			return this;
		}

		public LoanProductBuilder setMaxLoanAmount(double maxLoanAmount) {
			this.maxLoanAmount = maxLoanAmount;
			return this;
		}

	}

	public abstract double ltvCalculationAsPerCollateralType(
			double askedLoanAmount);

	public String getLoanProductCode() {
		return loanProductCode;
	}

	public String getLoanProductName() {
		return loanProductName;
	}

	public double getRoi() {
		return roi;
	}

	public double getLtv() {
		return ltv;
	}

	public boolean isAssetBased() {
		return assetBased;
	}

	public String getLoanSecurityType() {
		return loanSecurityType;
	}

	public double getMinTenure() {
		return minTenure;
	}

	public double getMaxTenure() {
		return maxTenure;
	}

	public double getMinLoanAmount() {
		return minLoanAmount;
	}

	public double getMaxLoanAmount() {
		return maxLoanAmount;
	}

	public LoanProduct setLoanProductCode(String loanProductCode) {
		this.loanProductCode = loanProductCode;
		return this;
	}

	public LoanProduct setLoanProductName(String loanProductName) {
		this.loanProductName = loanProductName;
		return this;
	}

	public LoanProduct setRoi(double roi) {
		this.roi = roi;
		return this;
	}

	public LoanProduct setLtv(double ltv) {
		this.ltv = ltv;
		return this;
	}

	public LoanProduct setAssetBased(boolean assetBased) {
		this.assetBased = assetBased;
		return this;
	}

	public LoanProduct setLoanSecurityType(String loanSecurityType) {
		this.loanSecurityType = loanSecurityType;
		return this;
	}

	public LoanProduct setMinTenure(double minTenure) {
		this.minTenure = minTenure;
		return this;
	}

	public LoanProduct setMaxTenure(double maxTenure) {
		this.maxTenure = maxTenure;
		return this;
	}

	public LoanProduct setMinLoanAmount(double minLoanAmount) {
		this.minLoanAmount = minLoanAmount;
		return this;
	}

	public LoanProduct setMaxLoanAmount(double maxLoanAmount) {
		this.maxLoanAmount = maxLoanAmount;
		return this;
	}

	@Override
	public String toString() {
		return "LoanProduct{" + "loanProductCode='" + loanProductCode + '\''
				+ ", loanProductName='" + loanProductName + '\'' + ", roi="
				+ roi + ", ltv=" + ltv + ", assetBased=" + assetBased
				+ ", loanSecurityType='" + loanSecurityType + '\''
				+ ", minTenure=" + minTenure + ", maxTenure=" + maxTenure
				+ ", minLoanAmount=" + minLoanAmount + ", maxLoanAmount="
				+ maxLoanAmount + '}';
	}
}
