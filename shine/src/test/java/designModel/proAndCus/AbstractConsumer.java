package designModel.proAndCus;

/**
 * 抽象的消费者
 *
 * @author wb-cb236432
 * @create 2018-07-24 17:02
 **/
public abstract class AbstractConsumer implements Consumer,Runnable{

    @Override
    public void run() {
        while (true){
            try {
                consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
