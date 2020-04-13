package threadspecial;

class Res {
    public static Integer count = 0;

    public static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>() {
        protected Integer initialValue() {
            return 0;
        }
    };

    public Integer getNum() {
        int count = threadLocal.get()+1;
        threadLocal.set(count);
        return count;
    }
}

class ThreadLoca implements Runnable {

    private Res res;

    public ThreadLoca(Res res) {
        this.res = res;
    }

    @Override
    public void run() {
        for (int i = 0;i<10;i++) {
            System.out.println(Thread.currentThread().getName()+"---"+i+"--"+res.getNum());
        }
    }
}

/**
 * ThreadLocal:提高一个线程的局部变量，使某个线程独立的使用某个变量
 *  get()：获取当前线程对应的局部变量的值
 *  set(count):设置当前线程的局部变量的值
 *  原理：Map.put("当前线程"，值)
 */
public class ThreadLocalDemo {

    public static void main(String[] args) {
        Res res = new Res();
        Thread t1 = new Thread(new ThreadLoca(res));
        Thread t2 = new Thread(new ThreadLoca(res));
        Thread t3 = new Thread(new ThreadLoca(res));
        t1.start();
        t2.start();
        t3.start();
    }

}
