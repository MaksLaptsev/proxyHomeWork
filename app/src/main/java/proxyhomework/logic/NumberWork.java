package proxyhomework.logic;

import proxyhomework.entity.Number;
import proxyhomework.psevdoSpring.annotations.Autowired;

public class NumberWork {
    @Autowired
    private Number number;

    public void printNumber(){
        System.out.println(number.getNumber() + " "+number.getName());
        System.out.println(number.getDoubleNumber()+ " is not a bad number");
        System.out.println("boolean = "+number.isTrue());
    }
}
