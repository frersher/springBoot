package designModel.adapter;

/**
 * @program: demo
 * @description: 客户端
 * @author: bang.chen
 * @create: 2018-07-22 23:57
 **/
public class Client {
    public static void main(String[] args) {
        Ios ios = new Adapter();
        ios.isIos();
    }
}
