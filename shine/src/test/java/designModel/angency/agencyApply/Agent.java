package designModel.angency.agencyApply;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by chenbang on 2019/12/9.
 * 代理对象
 */
public class Agent implements InvocationHandler {

    private BaseService baseService;


    public Agent(BaseService baseService) {
        this.baseService = baseService;
    }

    /**
     *
     * @param proxy 监听对象
     * @param method 监听方法
     * @param args 监听方法的参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        if (methodName.equals("eatFood")){
            wash();
            method.invoke(baseService, args);
        }else if(methodName.equals("wcing")){
            method.invoke(baseService, args);
            wash();
        }
        return null;
    }


    private void wash(){
        System.out.println("洗手");
    }
}
