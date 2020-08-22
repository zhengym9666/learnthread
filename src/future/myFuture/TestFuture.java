package future.myFuture;

public class TestFuture {

    public static void main(String[] args) {
        String result = new FutureClient().submit("测试future异步化请求返回结果");
        System.out.println("返回结果："+result);
    }

}
