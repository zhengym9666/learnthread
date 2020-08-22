class ThreadTrain3 implements Runnable{

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
     * 同步函数解决线程安全问题，函数声明中使用synchronized关键字修饰，相当于对该函数加了一个锁，
     * 当多个线程同时访问时，必须要获取到释放的锁才能访问，从而避免了共享数据冲突问题
     */
    //t1  t2   1，当count为1时，t1和t2线程都在等待执行，会导致多售出一张票
    public synchronized void sale() {
        //防止最后一个数减为0后，还有一个在等待锁进入执行
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

public class ThreadSynchronizedDemo2 {

    public static void main(String[] args) {
        ThreadTrain3 train3 = new ThreadTrain3();
        Thread thread1 = new Thread(train3);
        Thread thread2 = new Thread(train3);
        thread1.start();
        thread2.start();
    }

}
