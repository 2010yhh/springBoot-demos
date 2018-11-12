package com.ctg.test.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ctg.test.filter.MyFilter1;

/** 
 * @Description: 拦截器作用：
 * 作用于controller层,拦截到请求，对请求或响应(Request、Response)统一设置
 * 进行逻辑判断，如用户是否已经登陆、有没有权限访问该页面等
        */
public class MyInterceptor implements HandlerInterceptor {
	 protected Logger log = LoggerFactory.getLogger(MyInterceptor.class);  
	//在请求处理之前进行调用（Controller方法调用之前
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
    	log.info("preHandle被调用");
        return true;    //如果false，停止流程，api被拦截
    }

    //请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    	log.info("postHandle被调用");
    }

    //在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    	log.info("afterCompletion被调用");
    }

}
