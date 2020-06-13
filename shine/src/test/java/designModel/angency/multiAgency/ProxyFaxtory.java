package designModel.angency.multiAgency;

import java.lang.reflect.Proxy;

/**
 * 代理对象（监听对象）
 *
 * @author cb
 * @since 2020/6/13
 */

public class ProxyFaxtory {

    public static BaseService newInstance(Class classFile) throws IllegalAccessException, InstantiationException {
        BaseService baseService = (BaseService) classFile.newInstance();

        Class classArray[] = {BaseService.class};

        Mantis mantis = new Mantis(baseService);

        BaseService baseService1 = (BaseService) Proxy.newProxyInstance(mantis.getClass().getClassLoader(), classArray, mantis);

        Sisikin sisikin = new Sisikin(baseService1);

        BaseService baseService2 = (BaseService) Proxy.newProxyInstance(sisikin.getClass().getClassLoader(), classArray, sisikin);
        return baseService2;

    }
}
