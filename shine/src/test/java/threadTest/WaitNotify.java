package ThreadTest;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 线程之间的通信
 *
 * @author wb-cb236432
 * @create 2018-06-28 11:30
 **/
public class WaitNotify {
    static boolean flag = true;
    static Object lock = new Object();

    public static void main(String[] args){
          Thread waitThread = new Thread(new Wait(),"waitThread");
          waitThread.start();
          Thread notifyThread = new Thread(new Notify(),"notifyThread");
          notifyThread.start();
    }

    static class Wait implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {
                while (flag) {
                    try {
                        System.out.println(Thread.currentThread() + "flag is true " + new SimpleDateFormat("HH:mm:ss")
                            .format(new Date()));
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread() + "flag is false " + new SimpleDateFormat("HH:mm:ss")
                    .format(new Date()));
            }
        }
    }

    static class Notify implements  Runnable{
        @Override
        public void run() {
            synchronized(lock){
                while(flag){
                    System.out.println(Thread.currentThread() + "hold lock " + new SimpleDateFormat("HH:mm:ss")
                        .format(new Date()));
                    lock.notifyAll();
                    flag= false;
                    SleepUtils.second(5);
                }
                synchronized (lock){
                    System.out.println(Thread.currentThread() + "hold lock again" + new SimpleDateFormat("HH:mm:ss")
                        .format(new Date()));
                    SleepUtils.second(5);
                }
            }
        }
    }

}
