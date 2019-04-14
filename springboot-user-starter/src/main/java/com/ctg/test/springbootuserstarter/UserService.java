package com.ctg.test.springbootuserstarter;

/**
 * 自动配置服务类
 */
public class UserService {
    private String userName;
    private String passWord;
    public UserService(String userName,String passWord) {
        this.userName =userName;
        this.passWord =passWord;
    }
    public void print(){
        System.out.println("userName:"+userName);
        System.out.println("passWord:"+passWord);
    }
}
