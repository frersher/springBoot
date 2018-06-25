package designModel.deco.component;

/**
 * 烧饼
 * @author wb-cb236432
 * @create 2018-06-22 16:40
 **/
public class BrakeBread extends Bread{

    public  BrakeBread(){
        name = "烧饼";
    }

    @Override
    public int price() {
        return 2;
    }
}
