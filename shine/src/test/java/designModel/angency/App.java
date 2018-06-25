package designModel.angency;

/**
 * @program: demo
 * @description: 客户端
 * @author: bang.chen
 * @create: 2018-06-25 22:27
 **/
public class App {
    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        UserProxy userProxy = new UserProxy(userDao);

        userProxy.save();
    }
}
