package designModel.decoration;

import designModel.component.Bread;

/**
 * 加生菜
 *
 * @author wb-cb236432
 * @create 2018-06-22 16:48
 **/
public class AddLettuce extends Decorator{

    public AddLettuce(Bread bread) {
        super(bread);
    }

    public void addFood(){
        System.out.println("加一点生菜.....");
    }


    @Override
    public int price() {
        return bread.price()+1;
    }

    @Override
    public  String getName(){
        addFood();
        return "加了生菜的"+bread.getName();
    }
}
