package threadspecial;

class ThreadNoAtomic implements Runnable {

    private static volatile int count = 0;

    @Override
    public void run() {

        for (int i = 0;i<100;i++) {
            count++;
        }
        System.out.println(count);
    }
}

/**
 * volatile不具备原子性
 * 运行多次，会出现如下数据不一致的情况：
 * 1300
 * 1400
 * 1100
 * 1500
 */
public class ThreadVolatileNoAtomic {

    public static void main(String[] args) {
        Thread[] arr = new Thread[20];
        for (int i = 0;i<20;i++) {
            ThreadNoAtomic threadNoAtomic = new ThreadNoAtomic();
            arr[i] = new Thread(threadNoAtomic);
        }

        for (int i = 0;i<20;i++) {
            arr[i].start();
        }
    }

}
