package proxyhomework.service.serviceImpl;

import proxyhomework.anotation.Log;
import proxyhomework.anotation.MakeProxy;
import proxyhomework.dao.Dao;
import proxyhomework.dao.daoImpl.HospitalDaoImpl;
import proxyhomework.entity.Hospital;
import proxyhomework.service.Service;

import java.util.List;

@MakeProxy
public class HospitalServiceImpl implements Service<Hospital> {
    private final Dao<Hospital> hospitalDao;

    public HospitalServiceImpl() {
        this(new HospitalDaoImpl());
    }

    public HospitalServiceImpl(Dao<Hospital> hospitalDao) {
        this.hospitalDao = hospitalDao;
    }

    @Override
    @Log
    public Hospital getById(int id) {
        return hospitalDao.getById(id);
    }

    @Override
    public List<Hospital> getAll() {
        return hospitalDao.getAll();
    }

    @Override
    public void deleteById(int id) {
        hospitalDao.deleteById(id);
    }

    @Override
    public void save(Hospital h) {
        hospitalDao.save(h);
    }
}
