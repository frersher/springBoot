package designModel.angency.multiAgency;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 螳螂
 *
 * @author
 * @since 2020/6/13
 */

public class Mantis implements InvocationHandler {

    private BaseService cicada;

    public Mantis(BaseService cicada) {
        this.cicada = cicada;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        method.invoke(cicada, args);
        eatCicada();
        return null;
    }


    private void eatCicada(){
        System.out.println("螳螂捕蝉......");
    }

}
