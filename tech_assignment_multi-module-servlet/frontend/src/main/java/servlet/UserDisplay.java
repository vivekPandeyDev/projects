package servlet;

import dao.UserDao;
import dao.UserDaoImpl;
import entity.User;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user")
@Log4j2
public class UserDisplay  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDao userDao = new UserDaoImpl();
        Integer userId =  Integer.parseInt(req.getParameter("userId"));
        User user =  userDao.get(userId);
        log.info("user info ,{}",user);
        req.setAttribute("user",user);
        req.getRequestDispatcher("display.jsp").forward(req,resp);
    }
}
