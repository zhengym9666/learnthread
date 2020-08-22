package future.myFuture;

public class FutureClient {

    public String submit(String requestData) {
        FutureData futureData = new FutureData();
        //通过创建新的线程来执行请求，不影响主线程的执行
        new Thread(new Runnable() {
            @Override
            public void run() {
                RealData realData = new RealData(requestData);
                futureData.setRealData(realData);
            }
        }).start();
        System.out.println("未获取到请求结果，线程进入等待...");
        //这里的getRequest方法实质上就是Future的get方法，会阻塞，本质上就是通过wait方法进入等待实现阻塞
        String result = futureData.getRequest();
        System.out.println("future获取到了返回结果，返回给客户端");
        return result;
    }

}
