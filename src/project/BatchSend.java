package project;


import project.entity.UserEntity;
import project.thread.UserThread;
import project.util.ListUtils;

import java.util.ArrayList;
import java.util.List;

public class BatchSend {

    public static List<UserEntity> init() {
        List<UserEntity>list = new ArrayList<UserEntity>();
        for (int i = 1; i<= 140; i++) {
            UserEntity userEntity = new UserEntity();
            userEntity.setUserId("userId" + i);
            userEntity.setUserName("userName" + i);
            list.add(userEntity);
        }
        return list;
    }

    public static void main(String[] args) {
        //1、初始化用户数据
        List<UserEntity> userList = init();
        //2、计算创建多少线程，并且每个线程分批执行任务
        int userPageSize = 50;
        List<List<UserEntity>> splitUserList = ListUtils.splitList(userList,userPageSize);
        for (int i = 0;i<splitUserList.size();i++) {
            List<UserEntity> toSendList = splitUserList.get(i);
            Thread thread = new Thread(new UserThread(toSendList));
            //线程执行任务
            thread.start();
        }
    }
}
