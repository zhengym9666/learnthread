package lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Cache {

    static private volatile Map<String,String> map = new HashMap<>();//保证每次都从主内存中取

    static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();//读写锁
    static Lock r = rwl.readLock();//读锁，读锁可以共存
    static Lock w = rwl.writeLock();//写锁不能共存

    /**
     * 读
     * @param key
     */
    public static void get(String key) {
        try {
            r.lock();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
            System.out.println("正在读取key:"+key+"开始");
            String value = map.get(key);
            System.out.println("正在读取key:"+key+"value:"+value+"结束");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            r.unlock();
        }
    }

    /**
     * 写
     * @param key
     * @param value
     */
    public static void put(String key,String value) {
        try {
            w.lock();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
            System.out.println("正在写入key:"+key+"value:"+value+"开始");
            map.put(key,value);
            System.out.println("正在写入key:"+key+"value:"+value+"结束");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            w.unlock();
        }
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    Cache.put(i+"",i+"");
                }
            }
        });
        thread1.start();
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    Cache.put(i+"",i+"");
                }
            }
        });
        thread2.start();
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    Cache.get(i+"");
                }
            }
        });
        thread3.start();
    }

}
