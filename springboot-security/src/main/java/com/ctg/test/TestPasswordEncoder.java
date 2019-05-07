package com.ctg.test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Description:BCryptPasswordEncoder方法采用SHA-256 +随机盐+密钥对密码进行加密,其过程是不可逆的
 * 可以使用PasswordEncoder对要保存在数据库中的密码进行加密，Security会自动对输入密码进行加密进行匹配
 * @Author: yanhonghai
 * @Date: 2019/4/15 9:14
 */
public class TestPasswordEncoder {
    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encoderStr = bCryptPasswordEncoder.encode("123456");
        System.out.println(encoderStr);
    }
}
