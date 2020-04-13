class ThreadDeadLock implements Runnable{

    private int count = 100;//共享资源，火车票
    public boolean flag = true;//标志，区分同步代码块还是同步函数
    Object mutex = new Object();//自定义锁

    @Override
    public void run() {
        if (flag) {
            while (true) {
                synchronized (mutex) {
                    /**
                     * 死锁产生的原因：互相等待锁的释放
                     * 当flag为ture时，获取了obj锁，等待this锁，才能继续执行
                     * 当flag为false时，获取了this锁，等待obj锁，才能继续执行
                     * 上面互相等待锁的释放
                     */
                    sale();
                }
            }
        } else {
            while (true) {
                sale();
            }
        }
    }

    /**
     * 解决死锁：同步之间不要嵌套同步
     */
    public synchronized void sale() {
        synchronized (mutex) {
            if (count>0) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"出售了第"+(100-count+1)+"张票");
                count--;

            }
        }
    }
}

public class ThreadSynchronizedDemo4 {

    public static void main(String[] args) {
        ThreadDeadLock train = new ThreadDeadLock();//创建一个实例，多线程共享同一资源
        Thread thread1 = new Thread(train);
        Thread thread2 = new Thread(train);
        thread1.start();
        train.flag = false;
        thread2.start();
    }
}
