package com.ctg.test.util;

/**
 * @Description: TODO
 * @Author: yanhonghai
 * @Date: 2019/5/4 16:53
 */

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

public class ImageCode {

    // 图片
    private BufferedImage image;

    // 随机数
    private String code;

    // 过期时间
    private LocalDateTime expireTime;

    public ImageCode() {
    }

    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
        this.image = image;
        this.code = code;
        this.expireTime = expireTime;
    }

    // expireIn 过多久图形验证码 过期
    public ImageCode(BufferedImage image, String code, int expireIn) {
        this.image = image;
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    // 验证是否过期
    public boolean isExpire() {
        return LocalDateTime.now().isAfter(expireTime);
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }

}