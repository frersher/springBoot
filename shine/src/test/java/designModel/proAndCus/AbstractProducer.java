package designModel.proAndCus;

/**
 * 抽象的生产者
 *
 * @author wb-cb236432
 * @create 2018-07-24 16:59
 **/
public abstract class AbstractProducer implements Producer,Runnable{
    @Override
    public void run(){
        while (true){
            try {
                produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
