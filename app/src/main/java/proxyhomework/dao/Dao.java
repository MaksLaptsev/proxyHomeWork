package proxyhomework.dao;

import java.util.List;

public interface Dao <T>{
    T getById(int id);
    List<T> getAll();
    void deleteById(int id);
    void save(T o);
}
