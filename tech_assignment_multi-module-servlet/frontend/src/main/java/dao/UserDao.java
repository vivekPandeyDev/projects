package dao;


import entity.User;
import exception.UserExceptionHandler;

import java.util.List;

public interface UserDao {
    void save(User user) throws UserExceptionHandler;
    User get(int userId);
    List<User> getAll();
    int getUserId();
}
