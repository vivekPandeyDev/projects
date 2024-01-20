package controller.dao.interfaces;

import entity.LoanApplication;

public interface LoanApplicationDao {
    int generateLoanApplication(LoanApplication loanApplication);
    int getMaxApplicationNumber();
    String getLoanStatus(int loanId);
    int addLoanApplication(LoanApplication loanApplication);
    
}
