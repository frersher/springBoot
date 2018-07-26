package designModel.Strategy;

/**
 * 客户端
 *
 * @author wb-cb236432
 * @create 2018-07-26 14:32
 **/
public class Client {
    public static void main(String[] args){
        StudyFeeStrategy sfs1 = new GoodStudentStrategy();
        StudyFeeStrategy sfs2 = new BadStudentStrategy();

        StudyFee sf1 = new StudyFee(sfs1);
        StudyFee sf2 = new StudyFee(sfs2);

        System.out.println("好学生学费："+sf1.quote(10000));
        System.out.println("差学生学费："+sf2.quote(10000));

    }


}
