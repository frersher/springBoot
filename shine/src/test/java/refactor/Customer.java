package refactor;

import java.util.ArrayList;
import java.util.List;

/**
 * 顾客
 * @author wb-cb236432
 * @create 2018-06-28 17:27
 **/
public class Customer {
    private  String name;
    private List<Rental> rentals = new ArrayList<Rental>();

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String statement(){
        return null;
    }
}
