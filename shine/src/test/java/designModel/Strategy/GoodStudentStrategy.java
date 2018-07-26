package designModel.Strategy;

/**
 * 好学生收费策略
 *
 * @author wb-cb236432
 * @create 2018-07-26 14:24
 **/
public class GoodStudentStrategy implements StudyFeeStrategy {
    @Override
    public double studyFee(double price) {
        return price*0.5;
    }
}
