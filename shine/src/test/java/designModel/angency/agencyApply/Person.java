package designModel.angency.agencyApply;

/**
 * Created by chenbang on 2019/12/9.
 */
public class Person  implements BaseService{

    @Override
    public void eatFood(String food) {
        System.out.println("狼吞虎咽吃"+food);
    }

    @Override
    public void wcing(String str) {
        System.out.println("舒爽的"+str);
    }
}
