package controller.servlet;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.dao.impl.LoanApplicationImpl;
import controller.dao.interfaces.LoanApplicationDao;
import utility.ApplicationNumberIncorrectException;

/**
 * Servlet implementation class LoanStatus
 */
@WebServlet("/getLoanStatus")
public class LoanStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loanStatus = null;
		try {
			System.out.println(request.getParameter("id"));
			int loanId = Integer.parseInt(request.getParameter("id"));
			System.out.print(loanId);
			LoanApplicationDao loanApplicationDao = new LoanApplicationImpl();
			loanStatus = loanApplicationDao .getLoanStatus(loanId);
			if(loanStatus == null) {
				throw new ApplicationNumberIncorrectException("Loan Applicatin not found!!!!");
			}
	
		}catch (Exception e) {
			System.out.println(e.getMessage());
			throw new ApplicationNumberIncorrectException("Enter valid Loan Id");
			
		}
		
		response.getWriter().write("LoanStatus :" + loanStatus);
		
		
	}


}
