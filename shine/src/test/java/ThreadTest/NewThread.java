package ThreadTest;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * Created by chenbang on 2020/3/23.
 */
public class NewThread {

    private static class userRun implements Runnable{
        @Override
        public void run() {
            System.out.println("I am a runnnable");
        }
    }


    private static class useCall implements Callable{

        @Override
        public String call() {
            System.out.println("I am a callable");
            return "Callable";
        }
    }

    private static class useThread extends Thread{

        @Override
        public void run() {
            System.out.println("I am a thread");
        }

    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        userRun userRun = new userRun();
        useCall call = new useCall();
        useThread useThread = new useThread();
        new Thread(userRun).start();
        Thread t = new Thread(userRun);
        t.interrupt();
        new Thread(useThread).start();
        FutureTask futureTask = new FutureTask(call);
        new Thread(futureTask).start();
        System.out.println(futureTask.get());

    }

}
