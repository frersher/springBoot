package designModel.angency;

/**
 * @program: demo
 * @description: 测试动态代理
 * @author: bang.chen
 * @create: 2018-06-25 23:06
 **/
public class DynamicApp {
    public static void main(String[] args) {
        //目标对象
        IUserDao target = new UserDao();

        //目标对象创建代理对像
        IUserDao proxy =(IUserDao)new ProxyFactory(target).getProxyObject();


        proxy.save();


    }
}
