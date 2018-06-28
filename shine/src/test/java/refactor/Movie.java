package refactor;

/**
 * 影片
 *
 * @author wb-cb236432
 * @create 2018-06-28 17:28
 **/
public class Movie {
    private static final int CHILDRENS=2;
    private static final int REGULAR=0;
    private static final  int NEW_RELEASE=1;

    private String priceCode;
    private String title;

    public String getPriceCode() {
        return priceCode;
    }

    public void setPriceCode(String priceCode) {
        this.priceCode = priceCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
