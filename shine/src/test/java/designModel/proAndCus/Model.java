package designModel.proAndCus;

/**
 * 抽象工厂模型
 *
 * @author wb-cb236432
 * @create 2018-07-24 17:04
 **/
public interface Model {
    Runnable newRunnableProducer();
    Runnable newRunnableConsumer();
}
