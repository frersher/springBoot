package designModel.observe;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象主体
 *
 * @author wb-cb236432
 * @create 2018-08-03 16:33
 **/
public abstract class Subject {
    List<Observer> observers = new ArrayList<>();

    /**
     * 添加观察者
     *
     * @param observer
     */
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    /**
     * 删除观察者
     *
     * @param observer
     */
    public void detObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(String state) {
        for (Observer observer : observers) {
            observer.update(state);
        }
    }
}
