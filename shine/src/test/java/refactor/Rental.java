package refactor;

/**
 * 租赁
 *
 * @author wb-cb236432
 * @create 2018-06-28 17:33
 **/
public class Rental {
    private Movie movie;
    private int daysRented;

    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    public Movie getMovie() {
        return movie;
    }

    public int getDaysRented() {
        return daysRented;
    }


    public double getCharge(){
        double result =0L;
        switch (getMovie().getPriceCode()){
            case Movie.REGULAR:
                result+=2;
                if(getDaysRented()>2){
                    result+=(getDaysRented()-2)*1.5;
                }
                break;
            case Movie.CHILDRENS:
                result+=1.5;
                if(getDaysRented()>3){
                    result+=(getDaysRented()-3)*1.5;
                }
            case  Movie.NEW_RELEASE:
                result+=getDaysRented()*3;


        }
        return result;
    }
}
