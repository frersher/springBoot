package designModel.angency.agencyApply;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
/**
 * Created by chenbang on 2019/12/9.
 * 代理工厂
 */
public class ProxyFactory {
    public static BaseService newInstance(Class classFile) throws IllegalAccessException, InstantiationException {


        BaseService baseService = (BaseService) classFile.newInstance();
        //获取一个代理对象
        InvocationHandler agent = new Agent(baseService);

        //注册一个对特定行为监控的对象


        Class classArray[] = {BaseService.class};
        /**
         * classLoader:指向别监控的类文：被监听的类中接口件在内存中的地址
         * classArray
         */
        return (BaseService) Proxy.newProxyInstance(BaseService.class.getClassLoader(), classArray, agent);
    }

}
