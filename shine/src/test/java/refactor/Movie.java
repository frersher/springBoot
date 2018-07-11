package refactor;

/**
 * 影片
 *
 * @author wb-cb236432
 * @create 2018-06-28 17:28
 **/
public class Movie {
    public static final int CHILDRENS = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;

    private Price price;
    private String title;


    public Movie(int priceCode, String title) {
        this.title = title;
        setPriceCode(priceCode);
    }

    public int getPriceCode() {
        return price.getPriceCode();
    }

    public void setPriceCode(int arg) {
        switch (arg) {
            case REGULAR:
                price = new RegularPrice();
                break;
            case CHILDRENS:
                price = new ChildrensPrice();
            case NEW_RELEASE:
                price = new NewReleasePrice();
            default:
                throw new IllegalArgumentException("Incorrect Price Code");

        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    //计算价格
    public double getCharge(int daysRented) {
        return price.getCharge(daysRented);
    }


    //计算积分
    public int getFrequentPoints(int daysRented) {
        return price.getFrequentPoints(daysRented);
    }
}
