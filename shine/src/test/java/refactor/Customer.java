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
        double totalAmout =0L;
        int  frequentRenterPoints=0;
        String result = "Rental Record for "+getName()+"/n";

        for(Rental rental:rentals){
            double thisAmount =rental.getCharge();


            frequentRenterPoints++;
            result+="\t"+rental.getMovie().getTitle()+"\t"+String.valueOf(thisAmount)+"\n";
            totalAmout+=thisAmount;
        }
        result+="Amount owed is "+String.valueOf(totalAmout)+"\n";
        result+="you earned "+String.valueOf(frequentRenterPoints)+" frequent renter points "+"\n";


        return result;
    }


}
