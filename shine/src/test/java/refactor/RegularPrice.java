package refactor;

/**
 * @program: demo
 * @description: ${description}
 * @author: bang.chen
 * @create: 2018-07-11 23:18
 **/
public class RegularPrice extends Price {
    @Override
    int getPriceCode() {
        return Movie.REGULAR;
    }


     double getCharge(int daysRented) {
        double result = 2;
        if (daysRented > 2) {
            result += (daysRented - 2) * 1.5;
        }
        return result;
    }
}
