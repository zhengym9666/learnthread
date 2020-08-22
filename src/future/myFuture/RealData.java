package future.myFuture;

public class RealData extends Data {


    private String resultData;

    public RealData(String requestData) {
        System.out.println("开始进行网络请求....请求参数："+requestData);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("请求结束，正在返回结果...");
        this.resultData = "郑永梅";
    }

    @Override
    public String getRequest() {
        return this.resultData;
    }
}
