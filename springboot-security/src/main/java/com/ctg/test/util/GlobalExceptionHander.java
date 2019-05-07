package com.ctg.test.util;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description:controller 增强器
 * @Author: yanhonghai
 * @Date: 2018/12/16 22:28
 */

@ControllerAdvice
public class GlobalExceptionHander {
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public   Result errorHander1(HttpServletRequest request, HttpServletResponse response,Exception exception) {
        //返回自定义结果
        Result result=new Result();
        result.setCode(500);
        result.setMsg(ExceptionUtils.getFullStackTrace(exception));
        result.setData(null);
        return  result;

    }
    @ResponseBody
    @ExceptionHandler(value = BizException.class)
    public   Result errorHander(HttpServletRequest request, HttpServletResponse response, BizException exception) {
        //返回自定义结果
        Result result=new Result();
        result.setCode(exception.getCode());
        result.setMsg(ExceptionUtils.getFullStackTrace(exception));
        result.setData(null);
        return  result;
        //返回视图
//        if(!(request.getHeader("accept").contains("application/json")|| (request.getHeader("X-Requested-With")!=null
//                &&request.getHeader("X-Requested-With").contains("XMLHttpRequest"))))
//              {
//            ModelAndView modelAndView=new ModelAndView("error");
//            modelAndView.addObject("exception",result);
//            return modelAndView;
//        }else {
//            try {
//                response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
//                response.setCharacterEncoding("UTF-8");
//                response.setHeader("Content-Type","application/json");
//                response.getWriter().write(JSON.toJSONString(result));
//            }catch (IOException e){
//                e.printStackTrace();
//            }
//            return null;
//        }

    }
}