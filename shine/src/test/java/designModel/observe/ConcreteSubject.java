package designModel.observe;

/**
 * 具体的主题
 *
 * @author wb-cb236432
 * @create 2018-08-03 16:41
 **/
public class ConcreteSubject extends Subject{
    private String state;

    public String getState() {
        return state;
    }

    public void change(String newState){
         state =newState;
         System.out.println("主题状态："+state);
         notifyObservers(state);
    }
}
