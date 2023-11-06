package proxyhomework.service.serviceImpl;

import proxyhomework.anotation.Log;
import proxyhomework.anotation.MakeProxy;
import proxyhomework.dao.Dao;
import proxyhomework.dao.daoImpl.PersonDaoImpl;
import proxyhomework.entity.Person;
import proxyhomework.service.Service;

import java.util.List;

@MakeProxy
public class PersonServiceImpl implements Service<Person> {
    private final Dao<Person> personDao;

    public PersonServiceImpl() {
        this(new PersonDaoImpl());
        System.out.println("Create PersonService");
    }
    public PersonServiceImpl(Dao<Person> dao) {
        this.personDao = dao;
    }

    @Override
    @Log
    public Person getById(int id) {
        return personDao.getById(id);
    }

    @Override
    public List<Person> getAll() {
        return personDao.getAll();
    }

    @Override
    @Log
    public void deleteById(int id) {
        personDao.deleteById(id);
    }

    @Override
    @Log
    public void save(Person p) {
        personDao.save(p);
    }
}
