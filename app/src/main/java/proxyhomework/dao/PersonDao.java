package proxyhomework.dao;

import java.util.List;

public interface PersonDao <T>{
    T getById(int id);
    List<T> getAll();
    void deleteById(int id);
    void save(T o);
}
