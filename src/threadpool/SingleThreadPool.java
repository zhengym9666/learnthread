package threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * newSingleThreadExecutor单线程池：池子中只有一个线程，按先进先出顺序执行
 * 若该线程被中断，线程池不会新建一个线程，若线程死亡，则不会存在于池子中，线程池会新建一个线程。
 */
public class SingleThreadPool {

    public static void main(String[] args) {
        ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
        for (int i = 0;i<100;i++) {
            final int index = i;

            singleThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    if (index==3) {
                        Thread.currentThread().stop();
                    }
                    System.out.println(Thread.currentThread().getName()+"---"+"单线程池"+index);
                }
            });
        }
    }
}
