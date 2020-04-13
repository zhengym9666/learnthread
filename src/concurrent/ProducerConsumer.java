package concurrent;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

class Producer implements Runnable{
    volatile boolean flag = true;//线程运行标志,保证每次都从主内存中取
    private AtomicInteger count = new AtomicInteger();
    private BlockingQueue blockingQueue;

    public Producer(BlockingQueue _blockinQueue) {
        this.blockingQueue = _blockinQueue;
    }

    @Override
    public void run() {
        while (flag) {
            System.out.println("生产者线程"+Thread.currentThread().getName()+"启动...");
            String data = count.getAndIncrement()+"";
            System.out.println(Thread.currentThread().getName()+"正在往队列中加入数据，data:"+data);
            try {
                boolean offerFlag = blockingQueue.offer(data,2, TimeUnit.SECONDS);
                if (offerFlag) {
                    System.out.println(Thread.currentThread().getName()+"往队列添加数据，data:"+data+"成功.");
                }else {
                    System.out.println(Thread.currentThread().getName()+"往队列添加数据，data:"+data+"失败.");
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public void stopProducer() {
        flag = false;
        System.out.println("生产者线程"+Thread.currentThread().getName()+"已停止");
    }
}

class Consumer implements Runnable {
    private volatile boolean flag = true;
    private BlockingQueue blockingQueue;

    public Consumer(BlockingQueue _blockingQueue) {
        this.blockingQueue = _blockingQueue;
    }

    @Override
    public void run() {
        while (flag) {
            System.out.println("消费者线程"+Thread.currentThread().getName()+"已启动");
            System.out.println("正在从队列取出数据");
            try {
                Object data = blockingQueue.poll(2,TimeUnit.SECONDS);
                if (data!=null) {
                    System.out.println(Thread.currentThread().getName()+"从队列拿到一个数据，data:"+data);
                } else {
                    System.out.println(Thread.currentThread().getName()+"超过2秒未拿到数据");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopConsumer() {
        flag = false;
        System.out.println("消费者线程已停止");
    }
}

public class ProducerConsumer {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executors = Executors.newFixedThreadPool(3);
        BlockingQueue queue = new LinkedBlockingDeque();
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);
        executors.execute(new Thread(producer));
        executors.execute(new Thread(consumer));
        executors.execute(new Thread(consumer));

        Thread.sleep(10*1000);
        producer.stopProducer();
        consumer.stopConsumer();
    }
}
