package designModel.component;

/**
 * 装饰模式的顶级父类
 * @author wb-cb236432
 * @create 2018-06-22 16:36
 **/
public abstract class Bread {
    String name;

    //不同饼的价格不同
    public  abstract  int price();

    //获得饼的名字
    public  String getName(){
        return name;
    }
}
