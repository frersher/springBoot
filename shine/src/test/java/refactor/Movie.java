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


    //计算价格
    public double getCharge(int daysRented){
        double result =0L;
        switch (getPriceCode()){
            case Movie.REGULAR:
                result+=2;
                if(daysRented>2){
                    result+=(daysRented-2)*1.5;
                }
                break;
            case Movie.CHILDRENS:
                result+=1.5;
                if(daysRented>3){
                    result+=(daysRented-3)*1.5;
                }
            case  Movie.NEW_RELEASE:
                result+=daysRented*3;


        }
        return result;
    }


    //计算积分
    public   int getFrequentPoints(int daysRented){
        if(getPriceCode() == Movie.NEW_RELEASE && daysRented>1){
            return  2;
        }else{
            return  1;
        }
    }
}
