package com.ctg.test.Async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @Description: TODO
 * @Author: yanhonghai
 * @Date: 2018/11/8 18:32
 */
@Service
public class AsyncTaskService {
    @Async
    void execute(int i){
        System.out.print("线程ID"+Thread.currentThread().getId()+"执行序号:"+i+"\n");
    }
}
