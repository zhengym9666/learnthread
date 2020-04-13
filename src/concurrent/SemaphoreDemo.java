package concurrent;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * Semaphore:一种基于计数的信号量，可以设定一个信号量的阈值，多个线程可以竞争获得许可信号，做自己的申请后归还，当超出阈值后，
 * 限号申请将会阻塞，Semaphore会创建一个对象池，将阻塞的线程放进去，有空闲信号后，会将线程取出申请信号，不一定按先进先出顺序。
 * availablePermits函数用来获取当前可用的资源数量
 * wc.acquire(); //申请资源
 * wc.release();// 释放资源
 *
 * 需求：一个厕所只有3个坑位，但是有10个人来上厕所，那怎么办？假设10的人的编号分别为1-10，并且1号先到厕所，10号最后到厕所。
 * 那么1-3号来的时候必然有可用坑位，顺利如厕，4号来的时候需要看看前面3人是否有人出来了，如果有人出来，进去，否则等待。
 * 同样的道理，4-10号也需要等待正在上厕所的人出来后才能进去，并且谁先进去这得看等待的人是否有素质，是否能遵守先来先上的规则。
 */
class Person implements Runnable{

    private String name;
    private Semaphore wc;

    public Person(String name,Semaphore semaphore){
        this.name = name;
        this.wc = semaphore;
    }

    @Override
    public void run() {
        try {
            int count = wc.availablePermits();//获取可用的资源
            if (count>0) {
                System.out.println(name+"有茅坑了...");
            } else {
                System.out.println(name+"没有茅坑...");
            }
            wc.acquire();//申请资源，不一定按先到先上的顺序
            System.out.println(name+"轮到我上厕所了...");
            Thread.sleep(new Random().nextInt(1000));
            System.out.println(name+"我上完厕所了...");
            wc.release();//释放资源
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

public class SemaphoreDemo{
    public static void main(String[] args) {
        Semaphore wc = new Semaphore(3);
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Person(i+"",wc));
            thread.start();
        }
    }
}