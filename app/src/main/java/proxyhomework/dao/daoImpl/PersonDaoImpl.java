package proxyhomework.dao.daoImpl;

import proxyhomework.dao.PersonDao;
import proxyhomework.entity.Person;
import proxyhomework.psevdoSpring.annotations.InjectMap;
import proxyhomework.utils.LoadEntity;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PersonDaoImpl implements PersonDao<Person> {
    @InjectMap
    private Map<Integer,Person> personMap;

    public PersonDaoImpl() {
    }

    public PersonDaoImpl(String fileName){
        personMap = new LoadEntity<Person>().getListOfObjects(fileName,Person.class).stream()
                .collect(Collectors.toMap(Person::getId, Function.identity()));
    }

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
