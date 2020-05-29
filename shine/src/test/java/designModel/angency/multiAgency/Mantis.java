package designModel.angency.multiAgency;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by chenbang on 2019/12/9.
 * 螳螂
 */
public class Mantis implements InvocationHandler {

    private BaseService obj;

    public Mantis(BaseService obj){
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        eatCicada();
        method.invoke(obj, args);
        return null;
    }

    private void eatCicada(){
        System.out.println("螳螂捕蝉...");
    }
}
