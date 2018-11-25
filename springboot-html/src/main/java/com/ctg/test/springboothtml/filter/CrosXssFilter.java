package com.ctg.test.springboothtml.filter;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *跨域：由于浏览器的安全性限制，不允许AJAX访问 协议不同、域名不同、端口号不同的 数据接口;
 * 前后端都需要设置允许跨域
 *
 */
@WebFilter
public class CrosXssFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(CrosXssFilter.class);
@Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
    	request.setCharacterEncoding("utf-8");
    	response.setContentType("text/html;charset=utf-8");
    	//跨域设置
    	if(response instanceof HttpServletResponse){
    		HttpServletResponse httpServletResponse=(HttpServletResponse)response;
    		//通过在响应 header 中设置 ‘*’ 来允许来自所有域的跨域请求访问。
            httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
    		//通过对 Credentials 参数的设置，就可以保持跨域 Ajax 时的 Cookie
            //设置了Allow-Credentials，Allow-Origin就不能为*,需要指明具体的url域
            //httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
            //请求方式
            httpServletResponse.setHeader("Access-Control-Allow-Methods", "*");
            //（预检请求）的返回结果（即 Access-Control-Allow-Methods 和Access-Control-Allow-Headers 提供的信息） 可以被缓存多久
            httpServletResponse.setHeader("Access-Control-Max-Age", "86400");
            //首部字段用于预检请求的响应。其指明了实际请求中允许携带的首部字段
            httpServletResponse.setHeader("Access-Control-Allow-Headers", "*");
    	}
    	//sql,xss过滤
        HttpServletRequest httpServletRequest=(HttpServletRequest)request;
        logger.info("CrosXssFilter.......orignal url:{},ParameterMap:{}",httpServletRequest.getRequestURI(), JSONObject.toJSONString(httpServletRequest.getParameterMap()));
        XssHttpServletRequestWrapper xssHttpServletRequestWrapper=new XssHttpServletRequestWrapper(
                httpServletRequest);
        chain.doFilter(xssHttpServletRequestWrapper, response);
        logger.info("CrosXssFilter..........doFilter url:{},ParameterMap:{}",xssHttpServletRequestWrapper.getRequestURI(), JSONObject.toJSONString(xssHttpServletRequestWrapper.getParameterMap()));
    }
    @Override
    public void destroy() {

    }

}
