package designModel.template;

/**
 * 文科
 *
 * @author wb-cb236432
 * @create 2018-07-02 15:43
 **/
public class Arts extends ExamModel {
    @Override
    void testMath() {
        System.out.println("考文科数学......");
    }

    @Override
    void testSynthesis() {
        System.out.println("考文综......");
    }
}
