package com.shine.basic;

import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.core.Converter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author  bang.chen
 * @since 5/2/18
 */
public class BeanCopyUtil {

    private static Map<String, BeanCopier> beanCopiers = new ConcurrentHashMap<String, BeanCopier>();

    public static final <T> T beanCopy(Object source, T target) {
        return beanCopy(source, target, new Converter() {

            @Override
            public Object convert(Object value, Class target, Object context) {
                if (value == null) {
                    return null;
                }

                return objectCast(value, target);
            }
        });
    }

    private static final <T> T beanCopy(Object source, T target, Converter converter) {
        if (source == null || target == null) {
            return target;
        }

        boolean useConvert = converter != null;
        String beanKey = generateKey(source.getClass(), target.getClass(), useConvert);
        BeanCopier copier = null;

        if (!beanCopiers.containsKey(beanKey)) {
            copier = BeanCopier.create(source.getClass(), target.getClass(), useConvert);
            beanCopiers.put(beanKey, copier);

        } else {
            copier = beanCopiers.get(beanKey);
        }

        copier.copy(source, target, converter);
        return target;
    }

    private static String generateKey(Class<?> class1, Class<?> class2, boolean convert) {
        return class1.toString() + "_" + String.valueOf(convert) + "_" + class2.toString();
    }

    private static <T> T objectCast(Object source, Class<T> targetClass) {
        T target = null;
        if (source == null) {
            return target;

        } else if (source.getClass().equals(targetClass)) {
            target = (T)source;

        } else if (source.getClass().isEnum() && targetClass == String.class) {
            target = (T)((Enum)source).name();

        } else if (targetClass.isEnum() && !source.getClass().isEnum()) {
            target = null;
        } else if (targetClass.isInterface()) {
            target = null;
        } else {
            // object convert
            try {
                target = targetClass.newInstance();
            } catch (Exception e) {
                throw new RuntimeException("无法新建对象用于bean copy", e);
            }

            beanCopy(source, target);
        }
        return target;
    }

}
