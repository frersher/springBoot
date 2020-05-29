package designModel.angency.agencyApply;

/**
 * Created by chenbang on 2019/12/9.
 */
public class Dog implements BaseService{

    @Override
    public void eatFood(String food) {
        System.out.println("吭"+food);
    }

    @Override
    public void wcing(String str) {
        System.out.println("三腿立"+str);
    }
}
