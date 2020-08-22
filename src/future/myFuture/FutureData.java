package future.myFuture;

public class FutureData extends  Data{

    private boolean FLAG = false;
    private RealData realData;

    public synchronized void setRealData(RealData realData){
        if (FLAG) {
            //如果已经拿到过数据，直接返回
            return;
        }
        //如果没有拿到过数据，那么获取数据并赋值，唤醒其他线程来获取返回结果
        this.realData = realData;
        this.FLAG = true;
        notify();

    }

    @Override
    public synchronized String getRequest() {
        //如果没有结果返回，那么这里就会不断等待
        while (!FLAG) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //有返回结果了，会被前面的线程唤醒返回结果
        return this.realData.getRequest();

    }
}
