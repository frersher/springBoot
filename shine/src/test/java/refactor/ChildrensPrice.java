package refactor;

/**
 * @program: demo
 * @description: ${description}
 * @author: bang.chen
 * @create: 2018-07-11 23:18
 **/
public class ChildrensPrice extends Price {
    @Override
    int getPriceCode() {
        return Movie.CHILDRENS;
    }

    double getCharge(int daysRented) {
        double result = 1.5;
        if (daysRented > 3) {
            result += (daysRented - 3) * 1.5;
        }
        return result;
    }
}
