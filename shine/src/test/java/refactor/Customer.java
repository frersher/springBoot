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
        String result = "Rental Record for "+getName()+"/n";

        for(Rental rental:rentals){
            result+="\t"+rental.getMovie().getTitle()+"\t"+String.valueOf(rental.getCharge())+"\n";
        }
        result+="Amount owed is "+String.valueOf(getTotalCharge())+"\n";
        result+="you earned "+String.valueOf(getFrequentPoints())+" frequent renter points "+"\n";


        return result;
    }


    private double getTotalCharge(){
        double result = 0L;
        for (Rental rental:rentals){
            result+=rental.getCharge();
        }
        return result;
    }

    private double getFrequentPoints(){
        int  frequentRenterPoints=0;
        for (Rental rental:rentals){
            frequentRenterPoints+=rental.getFrequentPoints();
        }
        return  frequentRenterPoints;
    }

}
