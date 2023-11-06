package proxyhomework.logic;

import proxyhomework.entity.Hospital;
import proxyhomework.entity.Person;
import proxyhomework.service.Service;
import proxyhomework.service.serviceImpl.HospitalServiceImpl;
import proxyhomework.service.serviceImpl.PersonServiceImpl;

import java.util.Random;

public class HospitalVisit {

    private Service<Person> personService;
    private Service<Hospital> hospitalService;

    public HospitalVisit() {
        this(new PersonServiceImpl(),new HospitalServiceImpl());
    }

    public HospitalVisit(Service<Person> personService, Service<Hospital> hospitalService) {
        this.personService = personService;
        this.hospitalService = hospitalService;
    }


    public void visit(){
        visit(new Random().nextInt(12),new Random().nextInt(12));
    }

    public void visit(int personID,int hospitalID){
        Person p = personService.getById(personID);
        Hospital h = hospitalService.getById(hospitalID);

        if (p==null){
            System.out.println("No one came to the hospital \n");
        } else if (h==null) {
            System.out.println("Person didn't find a hospital \n");
        }else {
            System.out.println(p.getName()+ " visited "+h.getName()+" at "+h.getAddress()+"\n");
        }
    }
}
