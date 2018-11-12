package com.ctg.test.controller;

import com.ctg.test.annotation.MethodTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * localhost:8080/springboot-demo2/test1
 * localhost:8080/springboot-demo2/test2
 */
@Controller
@RequestMapping("/")
public class FirstController {
	private final Logger log = LoggerFactory.getLogger(FirstController.class);
    @RequestMapping("/test1")
    @ResponseBody
    @MethodTime
    public String test1(HttpServletRequest request, HttpServletResponse response)
    {
    	log.info("-->:springboot-demo2");
        return "test1";
    }
    @RequestMapping("/test2")
    @ResponseBody
    @MethodTime
    String test2()
    {
    	try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        return "test2";
    }
}