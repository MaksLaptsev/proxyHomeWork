package proxyhomework.service;

import java.util.List;

public interface Service <T>{
    T getById(int id);
    List<T> getAll();
    void deleteById(int id);
    void save(T o);
}
