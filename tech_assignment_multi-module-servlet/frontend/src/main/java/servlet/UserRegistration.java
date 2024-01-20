package servlet;


import adapter.LocalDateTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.UserDao;
import dao.UserDaoImpl;
import dto.UserDto;
import entity.User;
import exception.UserExceptionHandler;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

@WebServlet("/user/register")
@Log4j2
public class UserRegistration extends HttpServlet {
    private Gson gson;
    private UserDao userDao;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                .create();
        this.userDao = new UserDaoImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        resp.setContentType("text/html");
        String data = request.getParameter("para");

        try{
            User user = gson.fromJson(data, User.class);
            int userId =  userDao.getUserId();
            user.setUserId(userId);
            System.out.println("user ," + user);
            userDao.save(user);
        } catch (Exception exceptionHandler) {
            resp.setStatus(400);
            writer.println(exceptionHandler.getMessage());
            return;
        }
        writer.println("user data inserted");
    }


}
