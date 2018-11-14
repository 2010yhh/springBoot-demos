package com.ctg.test.service;

import com.ctg.test.exception.BizException;
import com.ctg.test.exception.ResultCode;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.stereotype.Service;

/**
 * @Description: dao service层往上抛异常，controller统一处理
 * @Author: yanhonghai
 * @Date: 2018/11/14 23:44
 */
@Service
public class TestErrorService {
    public int getExeptionInfo(int j){
     if(j==0){
         throw new BizException(ResultCode.ERROR,"参数为0!");
     }
     return 1/j;
    }
    public int getExeptionInfo2(int j){
        int result=0;
        try {
             result=1/j;
        } catch (Exception e) {
            throw new BizException(ResultCode.ERROR,ExceptionUtils.getFullStackTrace(e));
        }
        return result;
    }
}
