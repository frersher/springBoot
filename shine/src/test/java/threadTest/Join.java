package ThreadTest;

import java.util.concurrent.TimeUnit;

/**
 * Joinces
 *
 * @author wb-cb236432
 * @create 2018-06-28 15:23
 **/
public class Join {
    public static void main(String[] args){
        Thread previous = Thread.currentThread();
        for(int i=0;i<10;i++){
            Thread thread = new Thread(new Domino(previous),String.valueOf(i));
            thread.start();
            previous =thread;
        }
        TimeUnit.SECONDS.toSeconds(5);
        System.out.println(Thread.currentThread().getName()+" terminate ");
    }


    static  class Domino implements Runnable{
        private Thread thread;
        public  Domino(Thread thread){
            this.thread=thread;
        }
        @Override
        public void run() {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" terminate ");
        }
    }
}
