package refactor;

/**
 * 影片
 *
 * @author wb-cb236432
 * @create 2018-06-28 17:28
 **/
public class Movie {
    public static final int CHILDRENS=2;
    public static final int REGULAR=0;
    public static final  int NEW_RELEASE=1;

    private   int priceCode;
    private String title;

    public int getPriceCode() {
        return priceCode;
    }

    public void setPriceCode(int priceCode) {
        this.priceCode = priceCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
