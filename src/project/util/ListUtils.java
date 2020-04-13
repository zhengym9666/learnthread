package project.util;

import project.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 将list分页工具类
 */
public class ListUtils {

    /**
     *
     * @param list 切割集合
     * @param pageSize 分页长度
     * @param <T> 返回分页数据
     * @return
     */
   public static <T>  List<List<T>> splitList(List<T> list, int pageSize) {
       int size = list.size();
       int page = (size+(pageSize-1))/pageSize;
       List<List<T>> resList = new ArrayList<>();
       for (int i = 0;i<page;i++) {
           List<T> subList = new ArrayList<>();
           int start = i*pageSize;
           int end = (pageSize*(i+1))-1;
           while (start<=end && start<size) {
               subList.add(list.get(start));
               start++;
           }
           resList.add(subList);
       }
       return resList;
   }

    public static void main(String[] args) {
        List<UserEntity>list = new ArrayList<UserEntity>();
        for (int i = 1; i<= 13; i++) {
            UserEntity userEntity = new UserEntity();
            userEntity.setUserId("userId" + i);
            userEntity.setUserName("userName" + i);
            list.add(userEntity);
        }
        List<List<UserEntity>>splitUserList = splitList(list,5);
        for(List<UserEntity> l:splitUserList) {
            System.out.println("分页数据");
            for (UserEntity u:l) {
                System.out.println(u);
            }
        }
    }

}
