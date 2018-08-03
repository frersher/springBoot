package designModel.observe;

/**
 * 客户端
 *
 * @author wb-cb236432
 * @create 2018-08-03 16:46
 **/
public class Client {
    public static void main(String[] args){
        Observer observer = new ConcreteObserver();
        ConcreteSubject subject = new ConcreteSubject();
        subject.addObserver(observer);
        subject.change("hello!!111");
    }
}
