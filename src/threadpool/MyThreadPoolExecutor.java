package threadpool;


import java.util.concurrent.*;

public class MyThreadPoolExecutor {

    public static void main(String[] args) {
        //线程池原理
        //线程池中的最大线程数量=缓存队列大小+最大线程数
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 2, 0l, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(3));
        //线程1
        threadPoolExecutor.execute(new TestThread("线程1"));
        //线程2，线程数已经达到corepoolSize,那么线程2会进入缓存队列
        threadPoolExecutor.execute(new TestThread("线程2"));
        //线程3，缓存队列还没满，线程3继续进入缓存队列
        threadPoolExecutor.execute(new TestThread("线程3"));
        //线程4，缓存队列还没满，线程4继续进入缓存队列
        threadPoolExecutor.execute(new TestThread("线程4"));
        //线程5，缓存队列已满，但是当前线程没达到最大线程数，会创建一个新的线程
        threadPoolExecutor.execute(new TestThread("线程5"));
        //线程6，当前线程数达到了最大线程数，无法再继续创建，拒绝任务。
        threadPoolExecutor.execute(new TestThread("线程6"));

    }

}

class TestThread implements Runnable {

    private String threadName;

    public TestThread(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+","+threadName);
    }
}
