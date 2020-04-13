package threadconnect;

class ConnectPractice implements Runnable {

    @Override
    public void run() {
        for (int i = 0;i<100;i++) {
            System.out.println(Thread.currentThread().getName()+"---"+i);
        }
    }
}

//有T1、T2、T3三个线程，如何怎样保证T2在T1执行完后执行，T3在T2执行完后执行？
//思路：利用join()方法，让当前线程执行完，再走下面的线程
public class ThreadConnectPractice {

    public static void main(String[] args) {
        //方法一：同一类线程对象
        Thread t1 = new Thread(new ConnectPractice());
        Thread t2 = new Thread(new ConnectPractice());
        Thread t3 = new Thread(new ConnectPractice());

        try {
            t1.start();
            t1.join();
            t2.start();
            t2.join();
            t3.start();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //方法二:不同类线程对象
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0;i<100;i++) {
                    System.out.println(Thread.currentThread().getName()+"---"+i);
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    thread1.join();
                    System.out.println("等待t1先执行完成");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 0;i<100;i++) {
                    System.out.println(Thread.currentThread().getName()+"---"+i);
                }
            }
        });
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    thread2.join();
                    System.out.println("等待t2先执行完成");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 0;i<100;i++) {
                    System.out.println(Thread.currentThread().getName()+"---"+i);
                }
            }
        });
        thread1.start();
        thread2.start();
        thread3.start();
    }

}
