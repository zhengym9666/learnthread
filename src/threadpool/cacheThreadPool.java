package threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池：线程集合，当要执行新的任务时会复用空闲的线程，而不会每次都新建线程
 * newCachedThreadPool可缓存线程池：线程池无限大，当线程池有多余空间时，可回收，线程不够用时，新建线程
 *
 *
 * 要执行第二个任务时，若第一个线程已执行完，则复用第一个线程
 */
public class cacheThreadPool {

    public static void main(String[] args) {
        ExecutorService cacheThreadPool = Executors.newCachedThreadPool();
        for (int i = 0;i<1000;i++) {
            final int index = i;

            //相当于创建并启动一个线程，依据线程池空闲线程决定
            cacheThreadPool.execute(new Runnable() {
            @Override
            public void run() {

                    if (index == 3) {
                        Thread.currentThread().interrupt();
                    }
                    /*try {
                        Thread.currentThread().sleep(index*1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/
                    System.out.println(Thread.currentThread().getName()+"---"+index);
                }
            });
        }

    }

}
