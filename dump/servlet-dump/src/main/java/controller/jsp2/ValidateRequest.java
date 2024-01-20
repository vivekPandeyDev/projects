package controller.jsp2;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/productValidate")
public class ValidateRequest extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String name = req.getParameter("productName");
			String price = req.getParameter("productPrice");
			HttpSession session = req.getSession();
			boolean isValid = true;
			if (name == null || name.trim().isEmpty()) {
				session.setAttribute("msg", "name cannot be null!!!");
				isValid = false;
			} else if (price == null || price.trim().isEmpty() || Double.parseDouble(price) <= 0) {
				session.setAttribute("msg1", "enter valid price!!!");
				isValid = false;
			}
			if (isValid) {
				session.setAttribute("name", name);
				if (price != null)
					session.setAttribute("price", price);

//				question 1
//				resp.sendRedirect("jsp/jsp2/product.jsp");
//				question 2
				if (session.getAttribute("products") == null)
					session.setAttribute("products", new ArrayList<>());
//				req.getRequestDispatcher("jsp/jsp2/productBean.jsp").forward(req, resp);
// question 3
				req.getRequestDispatcher("jsp/jsp2/productJstl.jsp").forward(req, resp);

			} else
				resp.sendRedirect("html/jsp2/product.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
