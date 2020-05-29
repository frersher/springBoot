package ThreadTest;

import ThreadTest.SleepUtils;

/**
 * @program: demo
 * @description: ${description}
 * @author: bang.chen
 * @create: 2018-06-27 22:42
 **/
public class ThreadSate {

    public static void main(String[] args) {
        new Thread(new TimeWaiting(),"TimeWaiting").start();
        new Thread(new Waiting(),"Waiting").start();
        //一个线程获取锁 另一个等待
        new Thread(new Blocked(),"Blocked-1").start();
        new Thread(new Blocked(),"Blocked-2").start();
    }


    static  class TimeWaiting implements  Runnable{
        @Override
        public void run() {
             while (true){
                 SleepUtils.second(100);
             }
        }
    }

    static  class  Waiting implements  Runnable{
        @Override
        public void run() {
            while (true){
                synchronized (Waiting.class){
                    try {
                        Waiting.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

    static  class  Blocked implements  Runnable{
        @Override
        public void run() {
            synchronized (Blocked.class){
                while (true){
                    SleepUtils.second(100);
                }
            }
        }
    }



}
