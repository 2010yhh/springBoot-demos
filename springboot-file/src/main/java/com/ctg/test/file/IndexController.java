package com.ctg.test.file;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class IndexController {
   @RequestMapping("/")
    public String uploadFile( final HttpServletResponse response, final HttpServletRequest request){
        return "index";
    }



}
