package entity;

import org.springframework.stereotype.Component;
import utility.LoanStatus;


@Component
public class LoanAgreement {

    private int loanId;
    private String loanType;
    private double amount;
    private LoanStatus loanStatus;

    public LoanAgreement(int loanId, String loanType, double amount) {
	super();
	this.loanId = loanId;
	this.loanType = loanType;
	this.amount = amount;
    }

    public LoanAgreement() {
	super();
    }

    public LoanStatus getLoanStatus() {
	return loanStatus;
    }

    public void setLoanStatus(LoanStatus loanStatus) {
	this.loanStatus = loanStatus;
    }

    public int getLoanId() {
	return loanId;
    }

    public void setLoanId(int loanId) {
	this.loanId = loanId;
    }

    public String getLoanType() {
	return loanType;
    }

    public void setLoanType(String loanType) {
	this.loanType = loanType;
    }

    public double getAmount() {
	return amount;
    }

    public void setAmount(double amount) {
	this.amount = amount;
    }

    @Override
    public String toString() {
	return "LoanAgreement [loanId=" + loanId + ", loanType=" + loanType + ", amount=" + amount + "]";
    }

}
