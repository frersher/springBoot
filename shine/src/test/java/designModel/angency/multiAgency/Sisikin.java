package designModel.angency.multiAgency;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * TODO
 *
 * @author cb
 * @since 2020/6/13
 */

public class Sisikin implements InvocationHandler {

    private BaseService obj;

    public Sisikin(BaseService obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        method.invoke(obj,args);
        eatMantis();
        return null;
    }


    private void eatMantis(){
        System.out.println("黄雀在后......");
    }
}
