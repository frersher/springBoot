package designModel;

import designModel.component.BrakeBread;
import designModel.component.Bread;
import designModel.decoration.AddBarbecue;
import designModel.decoration.AddLettuce;

/**
 * 客户端
 *
 * @author wb-cb236432
 * @create 2018-06-22 16:59
 **/
public class Client {
    public static void main(String[] args){
        Bread bread= new BrakeBread();
        bread = new AddLettuce(bread);
        bread = new AddBarbecue(bread);
        System.out.println("食物名称:"+bread.getName()+"  价格:"+bread.price());
    }
}
