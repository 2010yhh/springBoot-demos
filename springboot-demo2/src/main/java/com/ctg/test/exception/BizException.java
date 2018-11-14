package com.ctg.test.exception;

public class BizException extends  RuntimeException {
    long code;
    String message;

    public BizException(long code, String message) {
        this.code = code;
        this.message = message;
    }

    public BizException(Throwable cause, long code, String message) {
        super(cause);
        this.code = code;
        this.message = message;
    }

    public BizException() {
    }

    public BizException(Throwable cause) {
        super(cause);
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "BizException{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
