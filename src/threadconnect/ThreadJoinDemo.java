package threadconnect;


class ThreadJoin implements Runnable {

    @Override
    public void run() {
        for (int i = 0;i<100;i++) {
            System.out.println(Thread.currentThread().getName()+"---"+i);
        }
    }
}

//join():让其他线程先进入等待状态，自己先执行
//必须启动了线程之后再调用join方法才会生效
public class ThreadJoinDemo {

    public static void main(String[] args) {
        Thread thread1 = new Thread(new ThreadJoin());
        Thread thread2 = new Thread(new ThreadJoin());
        thread1.start();

        try {
            thread1.join();//线程1先全部执行完成
            thread2.start();//再启动线程2，不能让线程2跟线程1同时启动，否则还来不及执行join方法，线程2已经执行
            thread2.join(1000);//线程2先执行1秒，主线程处于等待1秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0;i<100;i++) {
            System.out.println("main---"+i);
        }
    }


}
