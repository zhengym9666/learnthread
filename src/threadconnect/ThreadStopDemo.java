package threadconnect;

class ThreadStop implements Runnable {

    public boolean flag = true;

    //flag可能被多个线程操作，因此应该使用同步函数
    @Override
    public synchronized void run() {
        while (flag) {
            try {
                int[] arr = new int[1];
                System.out.println(arr[1]);
                wait();
            } catch (Exception e) {
                  stopDemo();
                  e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"is run...");
        }
    }

    /**
     * 停止线程
     */
    public void stopDemo() {
        flag = false;
    }
}

/**
 * 停止线程：
 * 1、在循环体中使用退出标志，即执行完run方法后终止线程，常用于异常处理中，为了避免循环抛出异常，使用一个标志进行中断
 * 2、使用interrupt方法中断，线程进入阻塞状态
 */
public class ThreadStopDemo {

    public static void main(String[] args) {
        ThreadStop threadStop = new ThreadStop();//因为有同步函数，因此应将对象作为共用资源
        Thread thread1 = new Thread(threadStop);
        Thread thread2 = new Thread(threadStop);
        thread1.start();
        thread2.start();
        int i = 0;
        while (true) {
            if (i == 300) {
                thread1.interrupt();
                thread2.interrupt();
                break;
            }
            System.out.println("--"+i+"");
            i++;
        }
    }

}
