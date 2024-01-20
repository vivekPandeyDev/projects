package org.learn.dao;

import org.learn.entity.User;
import org.learn.exception.UserExceptionHandler;
import java.util.List;


public interface UserDao {
    void save(User user) throws UserExceptionHandler;
    User get(int userId);
    List<User> getAll();
}
