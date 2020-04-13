package threadconnect;

//共同资源
class Person {
    public String name;
    public String age;
}

//输入线程
class InputThread implements Runnable {

    private Person p;

    //利用java的封装性，通过构造函数将外部的通一个对象传入进行操作
    public InputThread(Person p) {
        this.p = p;
    }


    @Override
    public void run() {
        int count = 0;
        while (true) {
            synchronized (p) {
                if (count==0) {
                    p.name = "小白";
                    p.age = "18";
                } else {
                    p.name = "小灰";
                    p.age = "22";
                }
                count = (count+1)%2;
            }

        }
    }
}

//输出线程
class OuputThread implements Runnable {

    private Person p;

    //利用java的封装性，通过构造函数将外部的通一个对象传入进行操作
    public OuputThread(Person p) {
        this.p = p;
    }


    @Override
    public void run() {
        while (true) {
            synchronized (p) {
                System.out.println(p.name+" "+p.age);
            }
        }
    }
}

//线程之间的通信
//多个线程对同一个资源进行操作，只是操作的动作不同。
public class ThreadConnectDemo1{

    public static void main(String[] args) {
        Person p = new Person();//线程操作的共同资源
        Thread inputThread = new Thread(new InputThread(p));
        Thread ouputThread = new Thread(new OuputThread(p));
        inputThread.start();
        ouputThread.start();
    }

}
