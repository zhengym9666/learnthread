
class ThreadTrain2 implements Runnable{

    Object mutex = new Object();//自定义多线程锁
    private int count = 100;//共享资源，火车票数

    @Override
    public void run() {
        while (count>0) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sale();
        }
    }

    /**
     * 同步代码块解决线程安全问题，首先自定义一个线程锁，可以是任意对象
     * 使用synchronized关键字锁住自定义线程锁，将可能产生线程安全问题（即对共享资源进行写操作）
     * 的代码括住。
     */
    public void sale() {
        synchronized (mutex) {
            if (count>0) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"出售第"+((100-count)+1)+"张票");
                count--;
            }
        }
    }
}

public class ThreadSynchronizedDemo1 {
    public static void main(String[] args) {
        ThreadTrain2 train2 = new ThreadTrain2();//定义一个实例，让多个线程共享同一个变量
        Thread thread1 = new Thread(train2);
        Thread thread2 = new Thread(train2);
        thread1.start();
        thread2.start();
    }
}
