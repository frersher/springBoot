package designModel.Strategy;

/**
 * 普通学生策略
 *
 * @author wb-cb236432
 * @create 2018-07-26 14:27
 **/
public class OrdinaryStudentStrategy implements StudyFeeStrategy{
    @Override
    public double studyFee(double price) {
        return price;
    }
}
