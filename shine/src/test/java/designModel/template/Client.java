package designModel.template;

/**
 * 客户端
 * @author wb-cb236432
 * @create 2018-07-02 15:45
 **/
public class Client {
    public static void main(String[] args){
        Arts arts = new Arts();
        Science science = new Science();
        arts.testProcess();
        science.testProcess();
    }
}
