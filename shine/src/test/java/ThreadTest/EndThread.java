package ThreadTest;

/**
 * Created by chenbang on 2020/3/23.
 */
public class EndThread {

    private static class UseRun extends Thread {

        public UseRun(String threadName) {
            super(threadName);
        }

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            while (!isInterrupted()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println(threadName + "isInterrupted flag is " + isInterrupted());
                    interrupt();
                    e.printStackTrace();
                }
                System.out.println(threadName + "is run");
            }

            System.out.println(threadName + "isInterrupted flag is " + isInterrupted());
        }
    }


    public static void main(String[] args) throws InterruptedException {
        Thread thread = new UseRun("endThread");
        thread.start();
        Thread.sleep(200);
        thread.interrupt();
    }

}
