package service;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ServiceLayer<T> {
    boolean save(T t);
    T getSingleEntity(int id);
    List<T> getAllEntity();

    List<T> getAllEntityByName(String name);
    boolean update(T t);
    boolean delete(int id);
}
