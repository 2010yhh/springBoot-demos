package com.ctg.test.springbootlistener;



import org.springframework.context.ApplicationEvent;

public class UserRegistryEvent extends ApplicationEvent {


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private String userName;

    public UserRegistryEvent(Object source, String userName) {
        super(source);
        this.userName = userName;
    }
}