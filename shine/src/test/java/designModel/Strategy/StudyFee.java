package designModel.Strategy;

/**
 * 学费
 *
 * @author wb-cb236432
 * @create 2018-07-26 14:29
 **/
public class StudyFee {
    private StudyFeeStrategy studyFeeStrategy;

    public StudyFee(StudyFeeStrategy studyFeeStrategy) {
        this.studyFeeStrategy = studyFeeStrategy;
    }

    public double quote(double price){
        return  studyFeeStrategy.studyFee(price);
    }
}
