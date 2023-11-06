package proxyhomework.service.serviceImpl;

import proxyhomework.anotation.Log;
import proxyhomework.anotation.MakeProxy;
import proxyhomework.dao.PersonDao;
import proxyhomework.entity.Person;
import proxyhomework.psevdoSpring.annotations.Autowired;
import proxyhomework.service.PersonService;

import java.util.List;

@MakeProxy
public class PersonServiceImpl implements PersonService<Person> {
    @Autowired
    private PersonDao<Person> personDao;

    public PersonServiceImpl() {
        System.out.println("Create PersonService");
    }
    public PersonServiceImpl(PersonDao<Person> dao) {
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
