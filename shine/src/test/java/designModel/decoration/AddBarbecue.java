package designModel.decoration;

import designModel.component.Bread;

/**
 * 加烤肠
 *
 * @author wb-cb236432
 * @create 2018-06-22 16:52
 **/
public class AddBarbecue extends  Decorator{

    public AddBarbecue(Bread bread) {
        super(bread);
    }

    public void  addMeat(){
        System.out.println("加一根烤肠...");
    }

    @Override
    public int price() {
        return bread.price()+2;
    }

    @Override
    public  String getName(){
        addMeat();
        return "加了烤肠的"+bread.getName();
    }
}
