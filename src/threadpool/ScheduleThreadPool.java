package threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * newScheduledThreadPool定长周期性线程池，调用.schedule方法只会执行一次，要周期性执行要调用scheduleAtFixedRate
 * 方法，若线程池没满，也没有空闲线程，则会新建线程，若线程池已满，且没有空闲线程，则会等待
 */
public class ScheduleThreadPool {

    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService scheduleThreadPool = Executors.newScheduledThreadPool(3);
        //相当于创建并启动一个线程，具有周期性，依据线程池长度及空闲线程决定
        scheduleThreadPool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"---"+"定时线程");
            }
        },1, 2,TimeUnit.SECONDS);

        Thread.sleep(6000);
        //shutdown会停止接收新任务，老任务还是会继续执行
        scheduleThreadPool.shutdown();
    }
}
