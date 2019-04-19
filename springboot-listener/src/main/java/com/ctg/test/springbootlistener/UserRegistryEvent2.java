package com.ctg.test.springbootlistener;



import org.springframework.context.ApplicationEvent;

public class UserRegistryEvent2 extends ApplicationEvent {


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private String userName;

    public UserRegistryEvent2(Object source, String userName) {
        super(source);
        this.userName = userName;
    }
}