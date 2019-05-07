package com.ctg.test.util;

import org.springframework.security.core.AuthenticationException;

/**
 * @Description: TODO
 * @Author: yanhonghai
 * @Date: 2019/5/4 16:45
 */
// AuthenticationException 是 spring security 身份验证过程中异常的基类
public class ImageCodeException extends AuthenticationException {

    private static final long serialVersionUID = -7932793974645209799L;

    public ImageCodeException(String msg) {
        super(msg);

    }

}