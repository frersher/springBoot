package refactor;

/**
 * @program: demo
 * @description: 价格
 * @author: bang.chen
 * @create: 2018-07-11 23:17
 **/
public abstract class Price {
    abstract int getPriceCode();

    abstract double getCharge(int daysRented);

    int getFrequentPoints(int daysRented) {
            return 1;
    }
}
