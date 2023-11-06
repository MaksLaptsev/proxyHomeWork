package proxyhomework.dao.daoImpl;

import proxyhomework.dao.Dao;
import proxyhomework.entity.Hospital;
import proxyhomework.utils.LoadEntity;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class HospitalDaoImpl implements Dao<Hospital> {
    private final Map<Integer,Hospital> hospitalMap;

    public HospitalDaoImpl() {
        this("JsonHospitalsList.json");
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
