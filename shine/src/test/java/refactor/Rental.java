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
}
