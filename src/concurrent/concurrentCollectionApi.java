package concurrent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Vector;
import java.util.concurrent.*;

public class concurrentCollectionApi {

    public static void main(String[] args) throws InterruptedException {
        /*Vector vector = new Vector();
        vector.add("vector");//vector线程安全
        ArrayList list = new ArrayList();
        list.add("list");//线程不安全
        HashMap map = new HashMap();
        map.put("map","map");//HashMap不安全
        Hashtable table = new Hashtable();
        table.put("table","table");//HashTable安全*/

        /*不阻塞队列*/
        /*ConcurrentLinkedDeque deque = new ConcurrentLinkedDeque();//无边界的双向链表队列，支持FIFO和FILO
        deque.offer("deque1");
        deque.offer("deque2");
        deque.offer("deque3");
        deque.offer("deque4");
        //从头获取元素，删除该元素
        System.out.println(deque.poll());
        //从头获取元素，不删除该元素
        System.out.println(deque.peek());
        //获取总长度
        System.out.println(deque.size());
        ConcurrentLinkedQueue queue = new ConcurrentLinkedQueue();//无边界的单向链表队列，只遵循FIFO
        queue.offer("queue1");
        queue.offer("queue2");
        queue.offer("queue3");
        queue.offer("queue4");
        //从头获取元素，删除该元素
        System.out.println(queue.poll());
        //从头获取元素，不删除该元素
        System.out.println(queue.peek());
        //获取总长度
        System.out.println(queue.size());*/

        /*阻塞队列,FIFO*/
        //队列已满时进行写操作会阻塞，队列为空时进行读操作会阻塞
        //不能超过初始化长度
        /*ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(3);
        arrayBlockingQueue.add("arrayBlockingQueue1");
        arrayBlockingQueue.add("arrayBlockingQueue2");
        //添加阻塞1秒,1秒后添加不了算超时，代码继续往下走
        arrayBlockingQueue.offer("arrayBlockingQueue3",1, TimeUnit.SECONDS);
        System.out.println(arrayBlockingQueue.peek());*/

       /* LinkedBlockingQueue linkedBlockingQueue =
                new LinkedBlockingQueue(3);//不指定长度时是无界队列
        //读取阻塞1秒，1秒后读不到元素算超时，代码继续往下走
        Object e = linkedBlockingQueue.poll(1,TimeUnit.SECONDS);
        if (e==null) {
            System.out.println("读取元素超时");
        }
        System.out.println();
        linkedBlockingQueue.add("linkedBlockingQueue1");
        linkedBlockingQueue.add("linkedBlockingQueue2");
        linkedBlockingQueue.add("linkedBlockingQueue3");
        //添加阻塞3秒,处理方法：超时退出，即3秒后添加不了算超时，代码继续往下走
        boolean flag = linkedBlockingQueue.offer("linkedBlockingQueue4",3,TimeUnit.SECONDS);
        if (!flag) {
            System.out.println("添加元素超时");
        }
        System.out.println(linkedBlockingQueue.poll());
        System.out.println(linkedBlockingQueue.size());*/

       //无容量队列，一个线程添加的元素必须要被消费，否则会阻塞
       SynchronousQueue synchronousQueue = new SynchronousQueue();
       synchronousQueue.offer("synchronousQueue1");
        try {
             synchronousQueue.offer("synchronousQueue2",2, TimeUnit.SECONDS);
        } catch (IllegalStateException e) {
            System.out.println("添加元素超时");
        }
        System.out.println(synchronousQueue.poll());

        /*阻塞队列的处理方法：
        *add(e)—>remove():抛出异常,是指当阻塞队列满时候，再往队列里插入元素，会抛出IllegalStateException(“Queue full”)异常。当队列为空时，从队列里获取元素时会抛出NoSuchElementException异常 。
        *offer(e)—>poll():返回特殊值：插入方法会返回是否成功，成功则返回true。移除方法，则是从队列里拿出一个元素，如果没有则返回null
        *offer(e,time,unit)—>poll(time,unit):超时退出：当阻塞队列满时，队列会阻塞生产者线程一段时间，如果超过一定的时间，生产者线程就会退出。
        *put(e)—>take():一直阻塞：当阻塞队列满时，如果生产者线程往队列里put元素，队列会一直阻塞生产者线程，直到拿到数据，或者响应中断退出。当队列空时，消费者线程试图从队列里take元素，队列也会阻塞消费者线程，直到队列可用。*/
    }

}
