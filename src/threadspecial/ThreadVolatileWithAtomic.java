package threadspecial;

import java.util.concurrent.atomic.AtomicInteger;

class ThreadWithAtomic implements Runnable {

    //AtomicInteger原子类保证对相应数据的原子性
    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public void run() {

        for (int i = 0;i<100;i++) {
            //自增
            atomicInteger.incrementAndGet();
        }
        System.out.println(atomicInteger);
    }
}

/**
 * volatile不具备原子性，只保证数据的可见性，即所有线程只从主内存中去去数据，不从自己的私有内存中去
 * 为解决volatile的原子性，可以使用synchronized加锁，或者变量类型为AtomicInteger等原子类
 *
 * AtomicInteger原子类自增自减操作是线程安全的
 * 线程安全并不代表线程是有序的，要保证线程的有序性使用join等线程通讯方法
 *
 * 高并发下变量自增自减存在线程安全问题，解决方法：
 * 可使用synchronized，但是加锁性能开销太大，使用AtomicInteger原子类会更好
 */
public class ThreadVolatileWithAtomic {

    public static void main(String[] args) {
        Thread[] arr = new Thread[20];
        for (int i = 0;i<20;i++) {
            ThreadWithAtomic threadWithAtomic = new ThreadWithAtomic();
            arr[i] = new Thread(threadWithAtomic);
        }

        for (int i = 0;i<20;i++) {
            arr[i].start();
        }
    }

}
