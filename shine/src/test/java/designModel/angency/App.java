package designModel.angency;

/**
 * @program: demo
 * @description: 客户端
 * @author: bang.chen
 * @create: 2018-06-25 22:27
 **/
public class App {
    public static void main(String[] args) {
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        UserProxy userProxy = new UserProxy(userDaoImpl);

        userProxy.save();
    }
}
