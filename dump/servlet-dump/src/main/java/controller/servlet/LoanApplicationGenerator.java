package controller.servlet;

import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import controller.dao.impl.LoanApplicationImpl;
import controller.dao.interfaces.LoanApplicationDao;
import entity.LoanApplication;

@WebServlet("/loanApplication")
public class LoanApplicationGenerator extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(LoanApplicationGenerator.class);

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		LoanApplicationDao loanApplicationDao = new LoanApplicationImpl();
		LoanApplication loanApplication = new LoanApplication();
		resp.setContentType("text/html");
		try {
			loanApplication.setApplicantName(req.getParameter("applicant_name"))
					.setApplicationEmail(req.getParameter("applicant_email"))
					.setApplicationContact(req.getParameter("application_contact"))
					.setLoanType(req.getParameter("loan_type"))
					.setLoanTenure(Integer.parseInt(req.getParameter("loan_tenure")))
					.setApplicationReason(String.join(" ", req.getParameterValues("reason_for_loan")))
					.setOrganizationName(req.getParameter("organization_name"))
					.setDesignation(req.getParameter("designation"))
					.setMonthlySalary(Integer.parseInt(req.getParameter("monthly_salary")));

			String messageString = valid(loanApplication);
			if (messageString.equals("valid")) {
				resp.getWriter().println(
						"Loan Application id : " + loanApplicationDao.generateLoanApplication(loanApplication));
			} else {
				req.getSession().setAttribute("msg", messageString);
				resp.sendRedirect("jsp/jsp2/error.jsp");
			}

		} catch (Exception e) {
			logger.error("Cannot generate loan application : {}", e.getMessage());
		}

	}

	public String valid(LoanApplication loanApplication) {
		String name = loanApplication.getApplicantName();
		String orgName = loanApplication.getOrganizationName();
		String designation = loanApplication.getDesignation();
		if (!isStringOnlyAlphabet(name)) {
			return "name is not alphabet";
		} else if (!isStringOnlyAlphabet(orgName)) {
			return "organization name is not alphabet";
		} else if (!isStringOnlyAlphabet(designation)) {
			return "designation is not alphabet";
		}

		return "valid";
	}

	public static boolean isStringOnlyAlphabet(String str) {
		str = str.trim();
		return ((!str.equals("")) && (str != null) && (str.matches("^[a-zA-Z]*$")));
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		try (PrintWriter out = resp.getWriter()) {
			out.write("get method!!!");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
}
