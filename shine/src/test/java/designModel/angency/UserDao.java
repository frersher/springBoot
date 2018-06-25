package designModel.angency;

/**
 * @program: demo
 * @description: 接口实现、目标对象
 * @author: bang.chen
 * @create: 2018-06-25 22:22
 **/
public class UserDao implements IUserDao{
    @Override
    public void save() {
        System.out.println("数据已经保存");
    }
}
