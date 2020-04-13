class ThreadSynchronizedCompare implements Runnable {

    private static int count = 100;//共享资源，火车票
    Object mutex = new Object();//自定义共享锁
    public boolean flag = true;//标志，区分使用同步代码块还是同步函数

    @Override
    public void run() {
        if (flag) {
            synchronized (this.getClass()) {
                while (count>0) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"出售了第"+(100-count+1)+"张票");
                    count--;
                }
            }

        } else {
            while (count>0) {
//                synchronized (mutex) {
//                    sale();
//                }
                sale2();
            }
        }
    }

    /**
     * Thread-0出售了第100张票
     * Thread-0出售了第101张票
     * 同步代码块和同步函数使用的并不是同一个锁
     * 同步代码块锁住的是自定义的一个变量（this明锁），而同步函数锁住的是整个对象（this锁）
     * 当多个线程去操作一个共享资源时，若同时使用同步代码块和同步函数，而没有共用同一个锁，同样会出现线程安全问题
     *
     * 改进：若要同时使用同步代码块和同步函数，那么同步代码块应使用this锁，即synchronized锁住的是this对象
     */
    public synchronized void sale() {
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

    /**
     * 静态同步函数使用的锁是当前类的字节码文件对象
     *
     * 同步函数有所属的对象，即this
     * 但是静态函数没有this这个概念，因为类一加载进内存静态函数就存在了，但是类字节码文件进入内存的时候要先封装对象，
     * 即字节码文件对象，因此静态同步函数使用的锁是字节码文件对象
     * 每个对象都有它的字节码文件对象
     *
     * 验证：使用同步代码块将this.getClass()或者类.class将字节码文件对象锁住，与静态同步函数一起使用，不会出现线程安全问题
     *
     */
    public synchronized static void sale2() {
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

public class ThreadSynchronizedDemo3 {

    public static void main(String[] args) throws Exception{
        ThreadSynchronizedCompare train = new ThreadSynchronizedCompare();//定义一个实例，多个线程共享同一资源
        Thread thread1 = new Thread(train);
        Thread thread2 = new Thread(train);
        thread1.start();
        Thread.sleep(20);
        train.flag = false;
        thread2.start();
    }
}
