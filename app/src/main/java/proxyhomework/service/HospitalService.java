package proxyhomework.service;

import java.util.List;

public interface HospitalService <T>{
    T getById(int id);
    List<T> getAll();
    void deleteById(int id);
    void save(T o);
}
