package designModel.angency;

/**
 * @program: demo
 * @description:代理类
 * @author: bang.chen
 * @create: 2018-06-25 22:24
 **/
public class UserProxy implements IUserDao{
    private IUserDao target;

    public UserProxy(IUserDao target){
         this.target = target;
    }

    @Override
    public void save() {
        System.out.println("before do something.....");
        target.save();
        System.out.println("after do something......");
    }
}
