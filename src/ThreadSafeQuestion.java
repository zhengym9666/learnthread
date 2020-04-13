
class ThreadTrain1 implements Runnable{

    private int count = 100;//共享资源，火车票数

    @Override
    public void run() {
        while (count>0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sale();
        }
    }

    /**
     * 线程安全问题：多个线程对同一个共用变量进行写操作时，会出现数据不一致的情况，即为线程安全问题
     * Thread-0出售第31张票
     * Thread-1出售第31张票
     */
    public void sale() {
        if (count>0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {

            }
            System.out.println(Thread.currentThread().getName()+"出售第"+((100-count)+1)+"张票");
            count--;
        }
    }
}


public class ThreadSafeQuestion {

    public static void main(String[] args) {
        ThreadTrain1 train = new ThreadTrain1();//定义一个实例，让多个线程共享同一个资源
        Thread thread1 = new Thread(train);
        Thread thread2 = new Thread(train);
        thread1.start();
        thread2.start();
    }

}
