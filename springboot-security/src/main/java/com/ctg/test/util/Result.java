package com.ctg.test.util;

/**
 * @Description: 统一接口返回值
 * @Author: yanhonghai
 * @Date: 2018/11/14 18:28
 */
public class Result<T> {

    /**
     * 错误码.
     */
    private long code;

    /**
     * 提示信息.
     */
    private String msg;

    /**
     * 具体的内容.
     */
    private T data;

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}