package designModel.angency.agencyApply;

/**
 * Created by chenbang on 2019/12/9.
 */
public class TestMain {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
//        BaseService service = ProxyFactory.newInstance();
//        service.eatFood("");
        BaseService xiaoming = ProxyFactory.newInstance(Person.class);
        xiaoming.eatFood("鸡");

        BaseService dog = ProxyFactory.newInstance(Dog.class);
        dog.eatFood("骨头");

    }
}
