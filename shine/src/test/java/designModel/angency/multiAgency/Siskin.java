package designModel.angency.multiAgency;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by chenbang on 2019/12/9.
 * 黄雀
 */
public class Siskin implements InvocationHandler {
    private BaseService obj;

    public Siskin(BaseService obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        method.invoke(obj, args);
        eatMantis();
        return null;
    }


    private void eatMantis(){
        System.out.println("黄雀在后捕螳螂...");
    }

}
