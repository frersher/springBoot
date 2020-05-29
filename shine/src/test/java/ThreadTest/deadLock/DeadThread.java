package ThreadTest.deadLock;

public class DeadThread implements Runnable {

    public String username;
    public Object lock1 = new Object();
    public Object lock2 = new Object();

    @Override
    public void run() {
        // TODO Auto-generated method stub  
        if (username.equals("a")) {
            synchronized (lock1) {
                try {
                    System.out.println("username = " + username);
                    System.out.println(Thread.currentThread().getName());
                    Thread.sleep(3000);
                } catch (Exception e) {
                    // TODO: handle exception  
                    e.printStackTrace();
                }
                synchronized (lock2) {
                    System.out.println("按lock1->lock2的顺序执行代码");
                }
            }
        }
        if (username.equals("b")) {
            synchronized (lock2) {
                try {
                    System.out.println("username = " + username);
                    System.out.println(Thread.currentThread().getName());
                    Thread.sleep(3000);

                } catch (Exception e) {
                    // TODO: handle exception  
                    e.printStackTrace();
                }
                synchronized (lock1) {
                    System.out.println("按lock2->lock1顺序执行代码");
                }
            }

        }
    }

    public void setFlag(String username) {
        this.username = username;
    }

    public static void main(String[] args) {

        DeadThread dt1 = new DeadThread();
        dt1.setFlag("a");
        Thread t1 = new Thread(dt1);
        t1.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        dt1.setFlag("b");
        Thread t2 = new Thread(dt1);

        t2.start();
    }

}