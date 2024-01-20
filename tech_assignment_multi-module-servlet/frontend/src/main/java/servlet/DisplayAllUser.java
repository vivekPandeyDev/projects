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
import java.util.List;

@WebServlet("/home")
@Log4j2
public class DisplayAllUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDao userDao = new UserDaoImpl();
        List<User> users =  userDao.getAll();
        log.info("user info ,{}",users);
        req.setAttribute("users",users);
        req.getRequestDispatcher("home.jsp").forward(req,resp);
    }
}
