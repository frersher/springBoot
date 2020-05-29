package ThreadTest;

/**
 * Created by chenbang on 2020/3/23.
 * ThreadLocal 测试
 */
public class UseThreadLocal {
    static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 1);


    /**
     * 运行3个线程
     */
    public void startThreadArray(){
        Thread[] threads = new Thread[3];
        for (int i=0;i<threads.length;i++){
            threads[i] = new Thread(new TestThread(i));
        }
        for (int i=0;i<threads.length;i++){
            threads[i].start();
        }
    }

    /**
     * 观察线程之间是否有影响
     */
    public static class TestThread implements Runnable{
        int id;

        public TestThread(int id){
            this.id = id;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+" :start");
            int s = threadLocal.get();
            s = s+id;
            threadLocal.set(s);
            System.out.println(Thread.currentThread().getName()+" "+threadLocal.get());
        }
    }


    public static void main(String[] args) {
        UseThreadLocal useThreadLocal = new UseThreadLocal();
        useThreadLocal.startThreadArray();
    }
}
