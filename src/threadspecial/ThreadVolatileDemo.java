package threadspecial;

class ThreadNoVol2 implements Runnable {

    public volatile boolean flag = true;

    @Override
    public void run() {
        System.out.println("子线程开始运行");
        while (flag) {
        }
        System.out.println("子线程运行结束");
    }

    public void setRunning(boolean flag) {
        this.flag = flag;
    }
}

//线程三大特性：原子性，可见性，有序性
//可见性：当多个线程修改同一个变量时，一个线程修改了变量的值之后，其他线程可以看到修改的值
//以下代码会产生多线程可见性的问题：
//每个线程有自己的私有内存，获取一个变量的值先是从私有内存拿，然后再从主内存中去取，当一个公共变量的值被修改放到
//主内存中后,线程可能还没来得及去主内存中取,先取了自己私有内存中的值,从而产生数据不一致问题.
//解决:使用volatile关键字修饰共用变量，使得每次取该变量的值都是从主内存中去取。
    public class ThreadVolatileDemo{
        public static void main(String[] args) throws InterruptedException {
            ThreadNoVol2 noVolatile = new ThreadNoVol2();
            Thread thread1 = new Thread(noVolatile);
            thread1.start();
            Thread.sleep(3000);
            noVolatile.setRunning(false);//由于flag被volatile修饰了，因此线程会从主线程中取到修改后的值不会再执行
            System.out.println("已经将flag设置为false");
            Thread.sleep(1000);

        }
}
