package designModel.template;

/**
 * 理科
 *
 * @author wb-cb236432
 * @create 2018-07-02 15:44
 **/
public class Science extends ExamModel {
    @Override
    void testMath() {
        System.out.println("考理科数学......");
    }

    @Override
    void testSynthesis() {
        System.out.println("考文综......");
    }
}
