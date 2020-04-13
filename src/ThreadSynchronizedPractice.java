class ThreadAddMinusJ implements Runnable {

    private int j = 100;//共享资源
    public boolean flag = true;//true--加，false--减
    public boolean type = true;//true--第一种，false--第二种

    @Override
    public void run() {
        if (flag) {
            if (type) {
                synchronized (this) {
                    while (true) {
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName()+"对j增加了1，结果是"+(++j));
                    }
                }
            } else {
                while (true) {
                    add();
                }
            }

        } else {
            if (type) {
                synchronized (this) {
                    while (true) {
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName()+"对j减少了1，结果是"+(--j));
                    }
                }
            } else {
                while (true) {
                    minus();
                }
            }
        }
    }

    public synchronized void add() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"对j增加了1，结果是"+(++j));
    }

    public synchronized void minus() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"对j减少了1，结果是"+(--j));
    }

}
public class ThreadSynchronizedPractice {

    public static void main(String[] args) throws Exception{
        ThreadAddMinusJ j = new ThreadAddMinusJ();
        Thread thread1 = new Thread(j);
        j.flag = true;
        thread1.start();
        Thread thread2 = new Thread(j);
        j.type = false;
        thread2.start();
        Thread.sleep(1000);
        Thread thread3 = new Thread(j);
        Thread thread4 = new Thread(j);
        j.type = true;
        j.flag = false;
        thread3.start();
        j.type = false;
        thread4.start();


    }
}
