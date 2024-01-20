package entity;

import utility.LoanStatus;

public class LoanApplication {
    private int applicationId;
    private String applicantName;
    private String applicationEmail;
    private String applicationContact;
    private String loanType;
    private int loanTenure;
    private String applicationReason;
    private String organizationName;
    private String designation;
    private int monthlySalary;
   
    public LoanApplication() {
    }
    
    



	public String getApplicantName() {
        return applicantName;
    }

    public LoanApplication setApplicantName(String applicantName) {
        this.applicantName = applicantName;
        return this;
    }

    public String getApplicationEmail() {
        return applicationEmail;
    }

    public LoanApplication setApplicationEmail(String applicationEmail) {
        this.applicationEmail = applicationEmail;
        return this;
    }

    public String getApplicationContact() {
        return applicationContact;
    }

    public LoanApplication setApplicationContact(String applicationContact) {
        this.applicationContact = applicationContact;
        return this;
    }

    public String getLoanType() {
        return loanType;
    }

    public LoanApplication setLoanType(String loanType) {
        this.loanType = loanType;
        return this;
    }

    public int getLoanTenure() {
        return loanTenure;
    }

    public LoanApplication setLoanTenure(int loanTenure) {
        this.loanTenure = loanTenure;
        return this;
    }

    public String getApplicationReason() {
        return applicationReason;
    }

    public LoanApplication setApplicationReason(String applicationReason) {
        this.applicationReason = applicationReason;
        return this;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public LoanApplication setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
        return this;
    }

    public String getDesignation() {
        return designation;
    }

    public LoanApplication setDesignation(String designation) {
        this.designation = designation;
        return this;
    }

    public int getMonthlySalary() {
        return monthlySalary;
    }

    public LoanApplication setMonthlySalary(int monthlySalary) {
        this.monthlySalary = monthlySalary;
        return this;
    }

    public int getApplicationId() {
        return applicationId;
    }

    public LoanApplication setApplicationId(int applicationId) {
        this.applicationId = applicationId;
        return this;
    }

    @Override
    public String toString() {
        return "LoanApplication{" +
                "applicationId=" + applicationId +
                ", applicationName='" + applicantName + '\'' +
                ", applicationEmail='" + applicationEmail + '\'' +
                ", applicationContact='" + applicationContact + '\'' +
                ", loanType='" + loanType + '\'' +
                ", loanTenure=" + loanTenure +
                ", applicationReason='" + applicationReason + '\'' +
                ", organizationName='" + organizationName + '\'' +
                ", designation='" + designation + '\'' +
                ", monthlySalary='" + monthlySalary + '\'' +
                '}';
    }
}
