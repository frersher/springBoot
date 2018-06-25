package designModel.deco.decoration;

import designModel.deco.component.Bread;

/**
 * 装饰者的父类
 *
 * @author wb-cb236432
 * @create 2018-06-22 16:45
 **/
public abstract class Decorator extends Bread{
      protected  Bread bread;

      public  Decorator(Bread bread){
         this.bread=bread;
      }

}
