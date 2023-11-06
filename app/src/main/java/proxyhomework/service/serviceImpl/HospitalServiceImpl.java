package proxyhomework.service.serviceImpl;

import proxyhomework.anotation.Log;
import proxyhomework.anotation.MakeProxy;
import proxyhomework.dao.HospitalDao;
import proxyhomework.entity.Hospital;
import proxyhomework.psevdoSpring.annotations.Autowired;
import proxyhomework.service.HospitalService;

import java.util.List;

@MakeProxy
public class HospitalServiceImpl implements HospitalService<Hospital> {
    @Autowired
    private HospitalDao<Hospital> hospitalDao;

    public HospitalServiceImpl() {
        System.out.println("Create HospitalServiceImpl");
    }

    public HospitalServiceImpl(HospitalDao<Hospital> hospitalDao) {
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
