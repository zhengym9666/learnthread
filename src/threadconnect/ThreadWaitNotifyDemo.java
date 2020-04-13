package threadconnect;

class Person2 {
    public String name;
    public String age;

    //线程通讯标识
    public boolean flag = false;
}

class ThreadInput implements Runnable {

    private Person2 p;

    public ThreadInput(Person2 p) {
        this.p = p;
    }

    @Override
    public void run() {
        int count = 0;
        while (true) {
            synchronized (p) {
                //flag为true时进入线程等待
                if (p.flag) {
                    try {
                        //wait,持有该对象的线程把对象的控制权交出去，然后处于等待状态
                        p.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //flag为false时进行赋值，并唤醒等待p资源的线程，既然要唤醒其他线程
                // 那么应该将flag置为true，使接下来运行其他线程，本线程进入等待
                if (count == 0) {
                    p.name = "小白";
                    p.age = "12";
                } else {
                    p.name = "小灰";
                    p.age = "15";
                }
                count = (count + 1) % 2;
                p.flag = true;
                //唤醒正在等待该对象控制权的线程继续运行
                p.notify();
            }
        }
    }
}

class ThreadOutput implements Runnable {

    private Person2 p;

    public ThreadOutput(Person2 p) {
        this.p = p;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (p) {
                //若为false，当前线程进入等待状态
                if (!p.flag) {
                    try {
                        p.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //若为true,输出对象，并唤醒其他正在等待p资源的线程
                //既然要唤醒其他线程，那么当前线程应要进入等待，flag置为false
                 System.out.println(p.name + " "+p.age);
                 p.flag = false;
                 p.notify();
                }
            }
    }
}

//wait，属于Object类，当前线程交出对象的控制权，即释放锁，进入等待状态
//notify，属于Object类，唤醒正在等待对象控制权的线程继续执行
//sleep，属于Thread类，当前线程暂停指定时间，让出cpu给其他线程，但是不会释放锁
public class ThreadWaitNotifyDemo {

    public static void main(String[] args) {
        Person2 p = new Person2();
        Thread threadInput = new Thread(new ThreadInput(p));
        Thread threadOutput = new Thread(new ThreadOutput(p));
        threadInput.start();
        threadOutput.start();
    }

}
