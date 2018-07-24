package designModel.proAndCus;

import java.beans.IntrospectionException;

/**
 * 生产者接口
 *
 * @author wb-cb236432
 * @create 2018-07-24 16:54
 **/
public interface Producer {
    void produce() throws InterruptedException;
}
