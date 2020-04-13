package threadconnect;

class ThreadPriority implements Runnable {

    @Override
    public void run() {
        for (int i = 0;i<100;i++) {
            System.out.println(Thread.currentThread().getName()+"---"+i);
        }
    }
}

//线程优先级：优先级越高，越优先被分配到空闲的cpu
//通过setPriority方法设置优先级，范围1-10，默认是5
public class ThreadPriorityDemo {

    public static void main(String[] args) {
        ThreadPriority threadPriority = new ThreadPriority();
        Thread thread1 = new Thread(threadPriority);
        Thread thread2 = new Thread(threadPriority);
        thread1.setPriority(10);
        thread1.start();
        thread2.start();
    }


}
