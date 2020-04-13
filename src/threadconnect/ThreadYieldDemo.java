package threadconnect;

class ThreadYield implements Runnable {

    @Override
    public void run() {
        for (int i = 0;i<100;i++) {
            System.out.println(Thread.currentThread().getName()+"---"+i);
        }
    }
}

//线程让步：暂停当前正在执行的线程，让其他线程先执行。
// 当前正在执行的线程进入可执行状态，以让其他具有同样优先级的线程得到运行机会
//调用yield()方法，有可能没有效果
//优先级和线程让步也可能没有效果
public class ThreadYieldDemo {

    public static void main(String[] args) {
        Thread thread1 = new Thread(new ThreadYield());
        Thread thread2 = new Thread(new ThreadYield());
        thread1.start();
        thread2.start();
        Thread.yield();

        for (int i = 0;i<100;i++) {
            System.out.println("main---"+i);
        }

    }

}
