package dao;

import java.util.List;

import org.springframework.stereotype.Component;


@Component
public interface Dao<T> {
    boolean save(T t);
    T getSingleEntity(int id);
    List<T> getAllEntity();

    List<T> getAllEntityByName(String name);
    boolean update(T t);
    boolean delete(int id);
}
