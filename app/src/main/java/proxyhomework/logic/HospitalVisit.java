package proxyhomework.logic;

import proxyhomework.entity.Hospital;
import proxyhomework.entity.Person;
import proxyhomework.psevdoSpring.annotations.Autowired;
import proxyhomework.service.HospitalService;
import proxyhomework.service.PersonService;

import java.util.Random;

public class HospitalVisit {
    @Autowired
    private PersonService<Person> personService;
    @Autowired
    private HospitalService<Hospital> hospitalService;

    public HospitalVisit() {
    }

    public HospitalVisit(PersonService<Person> personService, HospitalService<Hospital> hospitalService) {
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
            System.out.println("\nNo one came to the hospital \n");
        } else if (h==null) {
            System.out.println("\nPerson didn't find a hospital \n");
        }else {
            System.out.println("\n"+p.getName()+ " visited "+h.getName()+" at "+h.getAddress()+"\n");
        }
    }
}
