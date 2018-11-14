package com.ctg.test.controller;

import com.ctg.test.annotation.MethodTime;
import com.ctg.test.exception.BizException;
import com.ctg.test.exception.Result;
import com.ctg.test.exception.ResultCode;
import com.ctg.test.exception.ResultUtil;
import com.ctg.test.service.TestErrorService;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * http://localhost:8080/springboot-demo2/test3
 * http://localhost:8080/springboot-demo2/test4
 */
@Controller
@RequestMapping("/")
public class SecondController {
    private final Logger log = LoggerFactory.getLogger(SecondController.class);
    @Autowired
    TestErrorService testErrorService;

    @RequestMapping("/test3")
    @ResponseBody
    @MethodTime
    public Result test1(HttpServletRequest request, HttpServletResponse response) {
        try {
            testErrorService.getExeptionInfo(0);
        } catch (Exception e) {
            return ResultUtil.error(ResultCode.ERROR, ExceptionUtils.getFullStackTrace(e));
        }
        return ResultUtil.success("test3");
    }

    @RequestMapping("/test4")
    @ResponseBody
    @MethodTime
    public Result test3() throws BizException {
        try {
            testErrorService.getExeptionInfo2(0);
        } catch (Exception e) {
            return ResultUtil.error(ResultCode.ERROR, ExceptionUtils.getFullStackTrace(e));
        }
        return ResultUtil.success("test4");
    }
}