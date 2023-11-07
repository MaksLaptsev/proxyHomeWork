package proxyhomework.dao.daoImpl;

import proxyhomework.dao.PersonDao;
import proxyhomework.entity.Person;
import proxyhomework.psevdoSpring.annotations.InjectMap;
import java.util.List;
import java.util.Map;

public class PersonDaoImpl implements PersonDao<Person> {
    @InjectMap
    private Map<Integer,Person> personMap;

    @Override
    public Person getById(int id) {
        return personMap.get(id);
    }

    @Override
    public List<Person> getAll() {
        return personMap.values().stream().toList();
    }

    @Override
    public void deleteById(int id) {
        personMap.remove(id);
    }

    @Override
    public void save(Person p) {
        personMap.put(p.getId(),p);
    }
}
