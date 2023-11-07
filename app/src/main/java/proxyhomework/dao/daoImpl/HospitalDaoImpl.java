package proxyhomework.dao.daoImpl;

import proxyhomework.dao.HospitalDao;
import proxyhomework.entity.Hospital;
import proxyhomework.psevdoSpring.annotations.InjectMap;
import java.util.List;
import java.util.Map;

public class HospitalDaoImpl implements HospitalDao<Hospital> {
    @InjectMap
    private Map<Integer,Hospital> hospitalMap;

    @Override
    public Hospital getById(int id) {
        return hospitalMap.get(id);
    }

    @Override
    public List<Hospital> getAll() {
        return hospitalMap.values().stream().toList();
    }

    @Override
    public void deleteById(int id) {
        hospitalMap.remove(id);
    }

    @Override
    public void save(Hospital h) {
        hospitalMap.put(h.getId(),h);
    }
}
