
class CreateRunnable implements Runnable{
    @Override
    // run方法中编写 多线程需要执行的代码
    public void run() {
        for (int i = 0;i<10;i++) {
            System.out.println("run() i:"+i);
        }
    }
}

public class ThreadDemo2 {
    public static void main(String[] args) {
        System.out.println("多线程创建开始");
        // 1.创建一个线程
        CreateRunnable runnable =  new CreateRunnable();
        Thread thread = new Thread(runnable);
        System.out.println("多线程启动");
        // 2.开启线程是调用start方法，不是run方法
        thread.start();
        System.out.println("多线程结束");

    }
}
