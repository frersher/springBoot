package designModel.Strategy;

/**
 * 差生策略
 *
 * @author wb-cb236432
 * @create 2018-07-26 14:28
 **/
public class BadStudentStrategy implements StudyFeeStrategy {
    @Override
    public double studyFee(double price) {
        return price*2;
    }
}
