package ThreadTest.deadLock;

/**
 * Created by chenbang on 2020/3/25.
 */
public class DeadLock implements Runnable {
    public String nameStr;
    public Object lock1 = new Object();
    public Object lock2 = new Object();


    public void setNameStr(String nameStr) {
        this.nameStr = nameStr;
    }

    @Override
    public void run() {
      if(nameStr.equals("A")){
          synchronized (lock1) {
              try {
              System.out.println("username1 = " + nameStr);
              System.out.println("thread name = "+Thread.currentThread().getName());
              Thread.sleep(3000);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
              synchronized(lock2){
                  System.out.println("按lock1->lock2的顺序执行代码");
              }
          }
      }

        if(nameStr.equals("B")){
            synchronized(lock2){
                try {
                System.out.println("username2 = " + nameStr);
                System.out.println("thread name = "+Thread.currentThread().getName());
                Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized(lock1){
                    System.out.println("按lock2->lock1的顺序执行代码");
                }
            }
        }


    }

    public static void main(String[] args) {

        DeadLock dt1 = new DeadLock();
        dt1.setNameStr("A");
        Thread t1 = new Thread(dt1);
        t1.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        dt1.setNameStr("B");
        Thread t2 = new Thread(dt1);

        t2.start();
    }
}
