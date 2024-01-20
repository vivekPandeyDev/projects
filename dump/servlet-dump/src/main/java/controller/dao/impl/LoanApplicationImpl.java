package controller.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import connection.MyConnection;
import controller.dao.interfaces.LoanApplicationDao;
import entity.LoanApplication;


public class LoanApplicationImpl implements LoanApplicationDao {
    private static final Logger logger = LogManager.getLogger(LoanApplicationImpl.class);

    @Override
    public int addLoanApplication(LoanApplication loanApplication) {
        try (Connection connection = MyConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("Insert into vivek_loan_application(applicantName, applicantEmailId, contactNumber, loanType, tenure, reasonForLoan, organizationName, designation, monthlySalary) values (?,?,?,?,?,?,?,?,?) ")) {
            preparedStatement.setString(1, loanApplication.getApplicantName());
            preparedStatement.setString(2, loanApplication.getApplicationEmail());
            preparedStatement.setString(3, loanApplication.getApplicationContact());
            preparedStatement.setString(4, loanApplication.getLoanType());
            preparedStatement.setInt(5, loanApplication.getLoanTenure());
            preparedStatement.setString(6, loanApplication.getApplicationReason());
            preparedStatement.setString(7, loanApplication.getOrganizationName());
            preparedStatement.setString(8, loanApplication.getDesignation());
            preparedStatement.setInt(9, loanApplication.getMonthlySalary());
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            logger.error("cannot add loan application : {}", e.getMessage());
            return 0;
        }
        return 1;
    }

    @Override
    public int generateLoanApplication(LoanApplication loanApplication) {
        int val = addLoanApplication(loanApplication);
        if(val == 1){
            return getMaxApplicationNumber();
        }
        return -1;
    }

    @Override
    public int getMaxApplicationNumber() {
        String query = "select max(applicationId) from vivek_loan_application";
        int id = 1;
        try (
                Connection connection = MyConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt(1);
            }
        } catch (Exception e) {
            logger.error("Cannot get Max application number :{}", e.getMessage());
        }
        return id;
    }

	@Override
	public String getLoanStatus(int loanId) {
        String query = "select loanStatus from vivek_loan_application where applicationId=?";
        String loanStatus = null;
        try (
                Connection connection = MyConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                loanStatus = resultSet.getString(1);
            }
        } catch (Exception e) {
            logger.error("Cannot get Max application number :{}", e.getMessage());
        }
        return loanStatus;
	}
    
    
    
}
