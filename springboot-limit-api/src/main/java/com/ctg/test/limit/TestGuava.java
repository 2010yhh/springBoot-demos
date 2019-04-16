package com.ctg.test.limit;

import com.google.common.util.concurrent.RateLimiter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description: 许可证将按每秒许可证规定的固定速度分配，许可将被平滑地分发，
 * 若请求超过permitsPerSecond则RateLimiter按照每秒 1/permitsPerSecond 的速率释放许可
 * @Author: yanhonghai
 * @Date: 2019/4/16 12:30
 */
public class TestGuava {
    public static void main(String[] args) {
        String start = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        RateLimiter limiter = RateLimiter.create(1.0); // 这里的1表示每秒允许处理的量为1个
        for (int i = 1; i <= 10; i++) {
            limiter.acquire();// 请求RateLimiter, 超过permits会被阻塞
            System.out.println("execute.." + i);
        }
        String end = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        System.out.println("start time:" + start);
        System.out.println("end time:" + end);
    }
}
