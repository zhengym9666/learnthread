package threadconnect;

/**
 *守护线程：进程线程（主线程）停止，守护线程也会自动销毁
 * 使用setDaemon(true)方法设置线程为守护线程
 */
public class DeamonThread {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("我是子线程...");
            }
        });
        thread.setDaemon(true);
        thread.start();
        Thread.sleep(1000);
        for (int i = 0;i<300;i++) {
            System.out.println("我是主线程");
        }
        System.out.println("主线程执行完毕");
    }

}
