package designModel.angency;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @program: demo
 * @description: 动态代理对象、不需要实现接口 需要制定接口类型
 * @author: bang.chen
 * @create: 2018-06-25 22:41
 **/
public class ProxyFactory {

    private  Object target;

    public ProxyFactory(Object object) {
        this.target = object;
    }

    public Object getProxyObject(){
       return  Proxy.newProxyInstance(target.getClass().getClassLoader(),
                 target.getClass().getInterfaces(),new InvocationHandler(){
                   /**
                    *
                    * @param proxy 负责监听的对象
                    * @param method 被拦截的业务方法
                    * @param args 被拦截业务方法的实参
                    * @return
                    * @throws Throwable
                    */
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("before do something...");
                         Object returnValue= method.invoke(target,args);
                        System.out.println("after do something...");
                        return returnValue;
                    }
                });
    }
}



