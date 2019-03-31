package CyclicBarrier;

import java.util.Map;
import java.util.concurrent.*;

/**
 * Created by shchenbang on 2019/3/30
 *
 * @Description:
 */
public class BankWaterService implements Runnable{
    private CyclicBarrier c = new CyclicBarrier(4, this);

    private Executor executor = Executors.newFixedThreadPool(4);

    private ConcurrentHashMap<String,Integer> sheetBankWaterCount = new ConcurrentHashMap<>();


    private void count(){
        for(int i=0;i<4;i++){
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    sheetBankWaterCount.put(Thread.currentThread().getName(), 1);
                    try {
                        c.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }


    @Override
    public void run() {
        int result = 0;
        for(Map.Entry<String,Integer> sheet:sheetBankWaterCount.entrySet()){
            result += sheet.getValue();
        }
        System.out.println("result="+result);
    }


    public static void main(String[] args) {
        BankWaterService waterService = new BankWaterService();
        waterService.count();
    }


}
