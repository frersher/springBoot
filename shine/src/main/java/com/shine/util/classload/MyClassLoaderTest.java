package com.shine.util.classload;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import sun.misc.Resource;

/**
 * @author: chenb
 * @date: 2022/05/14A
 **/
public class MyClassLoaderTest {
   static class  MyClassLoader extends ClassLoader {

      private String classPath;

      public MyClassLoader(String classPath) {
         this.classPath = classPath;
      }

      /**
       * 通过类名得到类的二进制数组
       * @param name 类名
       * @return
       * @throws IOException
       */
      private byte[] loadByte(String name) throws IOException {
         name = name.replaceAll("\\.", "/");
         FileInputStream fis = new FileInputStream(
             classPath + "/" + name + ".class");
         int len = fis.available();
         byte[] data = new byte[len];
         fis.read(data);
         fis.close();
         return data;
      }

      protected Class<?> findClass(final String name) throws ClassNotFoundException {
         try {
            byte[] data = loadByte(name);
            // 根据类的二进制数组，构建类的信息
            return defineClass(name, data, 0, data.length);
         } catch (IOException e) {
            e.printStackTrace();
            throw new ClassNotFoundException();
         }
      }


      public static void main(String[] args) throws Exception {
         MyClassLoader classLoader = new MyClassLoader("/Users/cb/Desktop/citi");
         Class<?> clazz = classLoader.loadClass("com.shine.Test");
         Object object = clazz.newInstance();
         Method method = clazz.getDeclaredMethod("sout", null);
         method.invoke(object,null);
         System.out.println(clazz.getClassLoader().getClass().getName());
      }


   }

}
