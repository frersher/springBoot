package refactor;

/**
 * @program: demo
 * @description: ${description}
 * @author: bang.chen
 * @create: 2018-07-11 23:18
 **/
public class NewReleasePrice extends Price {
    @Override
    int getPriceCode() {
        return Movie.NEW_RELEASE;
    }

    double getCharge(int daysRented) {
        return daysRented * 3;
    }

    int getFrequentPoints(int daysRented) {
        return (daysRented > 2) ? 1 : 2;
    }
}
