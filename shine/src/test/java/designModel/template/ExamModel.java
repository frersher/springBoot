package designModel.template;

/**
 * 考试模板
 *
 * @author wb-cb236432
 * @create 2018-07-02 15:31
 **/
public abstract class ExamModel {
    /**
     * 考试流程
     * 申明为final不希望子类覆盖，改变考试流程
     */
    final  void testProcess(){
        testLanguage();
        testMath();
        testSynthesis();
        testEnglish();
    }

    //第一科：考语文
    void  testLanguage(){
       System.out.println("语文考试......");
    }

    //第二科:考数学
    abstract void testMath();

    //第三科：考综合
   abstract void  testSynthesis();

    //第四科
    void  testEnglish(){
        System.out.println("英语考试......");
    }
}
