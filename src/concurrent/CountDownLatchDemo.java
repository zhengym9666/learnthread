package concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch实现类似计数器的功能，有一个任务需要等其他N个任务执行完成后，再执行，那么
 * 可以使用该工具。
 */
public class CountDownLatchDemo {

    public static void main(String[] args) {
        //初始化计数器
        CountDownLatch count = new CountDownLatch(2);
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("子线程"+Thread.currentThread().getName()+"运行开始");
                count.countDown();//每次减1
                System.out.println("子线程"+Thread.currentThread().getName()+"运行结束");
            }
        });
        thread1.start();
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("子线程"+Thread.currentThread().getName()+"运行开始");
                count.countDown();//每次减1
                System.out.println("子线程"+Thread.currentThread().getName()+"运行结束");
            }
        });
        thread2.start();
        try {
            count.await();//当前方法调用阻塞主线程，count减为0后，阻塞变为运行状态
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("子线程执行完毕");
        System.out.println("主线程开始运行");
    }

}
