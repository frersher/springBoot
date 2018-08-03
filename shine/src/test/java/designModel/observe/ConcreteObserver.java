package designModel.observe;

/**
 * 具体的观察者
 *
 * @author wb-cb236432
 * @create 2018-08-03 16:43
 **/
public class ConcreteObserver implements Observer {
    //观察者状态
    private String objectState;

    @Override
    public void update(String state) {
        this.objectState = state;
        System.out.println("观察者状态：" + objectState);
    }
}
