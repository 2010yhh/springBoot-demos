package com.ctg.test.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * @Description: 自定义密码校验
 * @Author: yanhonghai
 * @Date: 2018/9/17 0:56
 */
public class CredentialsMatcher extends SimpleCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken utoken = (UsernamePasswordToken) token;
        //获得用户输入的密码:(实际可以采用加盐(salt)的方式去检验)
        String inPassword = new String(utoken.getPassword());
        //获得数据库中的密码,存储是用加密的形式存储的
        String dbPassword = (String) info.getCredentials();
        //这里简单进行密码的比对
        return this.equals(inPassword, dbPassword);
    }

}