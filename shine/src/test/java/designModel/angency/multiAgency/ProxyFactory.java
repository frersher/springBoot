package designModel.angency.multiAgency;

import java.lang.reflect.Proxy;

/**
 * Created by chenbang on 2019/12/9.
 * 代理工厂
 */
public class ProxyFactory {

    public static BaseService newInstane(Class classFile) throws IllegalAccessException, InstantiationException {

        BaseService baseService = (BaseService) classFile.newInstance();

        Class classArray[] = {BaseService.class};

        Mantis mantis = new Mantis(baseService);

       BaseService baseService1 = (BaseService) Proxy.newProxyInstance(classFile.getClassLoader(), classArray, mantis);


        Siskin siskin = new Siskin(baseService1);

        BaseService baseService2 = (BaseService) Proxy.newProxyInstance(classFile.getClassLoader(), classArray, siskin);



       return baseService2;
    }
}
