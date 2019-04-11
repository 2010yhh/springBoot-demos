package com.ctg.test;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @Description: TODO
 * @Author: yanhonghai
 * @Date: 2018/11/19 15:54
 */
public class TestMD5 {
    public static void main(String[] args) {
        //加密方式
        String hashAlgorithmName = "MD5";
        //明文密码
        String credentials = "123456";
        int hashIterations = 1024;
        //盐值,这里的参数要给个唯一的;
        ByteSource credentialsSalt = ByteSource.Util.bytes("user2");
        Object obj = new SimpleHash(hashAlgorithmName, credentials, credentialsSalt, hashIterations);
        //加密后的密码
        System.out.println(obj);
    }
}
