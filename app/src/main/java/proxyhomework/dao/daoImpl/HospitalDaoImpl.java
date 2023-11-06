package proxyhomework.dao.daoImpl;

import proxyhomework.dao.HospitalDao;
import proxyhomework.entity.Hospital;
import proxyhomework.psevdoSpring.annotations.InjectMap;
import proxyhomework.utils.LoadEntity;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class HospitalDaoImpl implements HospitalDao<Hospital> {
    @InjectMap
    private Map<Integer,Hospital> hospitalMap;

    public HospitalDaoImpl() {
    }

    public HospitalDaoImpl(String fileName) {
        this.hospitalMap = new LoadEntity<Hospital>().getListOfObjects(fileName,Hospital.class).stream()
                .collect(Collectors.toMap(Hospital::getId, Function.identity()));
    }

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
