package project.thread;

import project.entity.UserEntity;

import java.util.List;

public class UserThread implements Runnable {

    private List<UserEntity> userList;

    /**
     * 通过构造函数，传入每个线程需要发送短信的用户集合
     * @param userList
     */
    public UserThread(List<UserEntity> userList) {
        this.userList = userList;
    }

    /**
     * 分批发送短信
     */
    @Override
    public void run() {
        for (UserEntity user:userList) {
            System.out.println("threadName:"+Thread.currentThread().getName()
            +"---用户编号："+user.getUserId()
            +"---用户名称："+user.getUserName());
            //调用发送短信接口
        }
    }
}
