package designModel.adapter;

/**
 * @program: demo
 * @description: 适配器
 * @author: bang.chen
 * @create: 2018-07-22 23:55
 **/
public class Adapter extends Androider implements Ios  {

    @Override
    public void isIos() {
        isAndroid();
    }
}
