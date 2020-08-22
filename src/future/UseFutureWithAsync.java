package future;

import java.util.concurrent.*;

public class UseFutureWithAsync {

    public static void main(String[] args) {
        //提交Future模式的线程任务，未来线程的执行结果可以拿到
        Future submit = Executors.newCachedThreadPool().submit(new AsyncThread());
        System.out.println("1.开始执行线程任务");
        //在submit的get方法之前可以做其他操作
        System.out.println("1.1在请求返回结果前主线程可以做其他操作");
        try {
            //get方法是阻塞的，后面的代码会阻塞在这里，要实现不影响主线程，可以创建新的线程来get
            String result = (String) submit.get();
            System.out.println("线程返回执行结果："+result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("2.线程执行结束");
    }

}

class AsyncThread implements Callable {

    @Override
    public String call() throws Exception {
        System.out.println("3.开始执行请求操作...");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("4.请求操作结束，返回执行结果...");
        return "郑永梅";
    }
}
