package threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *newFixedThreadPool定长线程池：池子中线程的数量是固定的，若池子中一个线程死亡，那么将会被回收，不计入池子线程数量
 * 此时池子会再创建一个新的线程
 * 若一个线程被中断（interrupt）,线程还未死亡，只是出于阻塞状态，池子不会新创建一个线程
 */
public class fixedThreadPool {

    public static void main(String[] args) {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(1);
        for (int i = 0;i<1000;i++) {
            final int index = i;

            //相当于创建并启动一个线程，依据线程池长度及空闲线程决定
            fixedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                    if (index==3) {
                        Thread.currentThread().stop();
                        System.out.println(Thread.currentThread().isInterrupted());
                        System.out.println(Thread.currentThread().isAlive());
                    }
                    System.out.println(Thread.currentThread().getName()+"---"+index);
                }
            });
        }

    }

}
