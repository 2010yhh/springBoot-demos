package com.ctg.test.cros.springbootcros.filter;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * 跨域：由于浏览器的安全性限制，不允许AJAX访问 协议不同、域名不同、端口号不同的 数据接口;
 * 前后端都需要设置允许跨域
 */
@WebFilter
public class CrosXssFilter implements Filter {
    // 多个跨域域名设置
    public static final String[] ALLOW_DOMAIN = {"http://localhost:8080",
            "http://localhost:8090", "http://localhost:8081"};
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
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String originHeader = req.getHeader("Origin");
        if (Arrays.asList(ALLOW_DOMAIN).contains(originHeader)) {
            //通过在响应 header 中设置 ‘*’ 来允许来自所有域的跨域请求访问。
            res.setHeader("Access-Control-Allow-Origin", originHeader);
           //通过对 Credentials 参数的设置，就可以保持跨域 Ajax 时的 Cookie
            //设置了Allow-Credentials，Allow-Origin就不能为*,需要指明具体的url域
            res.setHeader("Access-Control-Allow-Credentials", "true");
            //请求方式
            res.setHeader("Access-Control-Allow-Methods", "*");
            //（预检请求）的返回结果（即 Access-Control-Allow-Methods 和Access-Control-Allow-Headers 提供的信息） 可以被缓存多久
            res.setHeader("Access-Control-Max-Age", "86400");
            //首部字段用于预检请求的响应。其指明了实际请求中允许携带的首部字段
            //res.setHeader("Access-Control-Allow-Headers", "*");
            res.setHeader("Access-Control-Allow-Headers", "Timestamp,Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token,Access-Control-Allow-Headers");
        }
        //sql,xss过滤
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        Cookie[] cookies =  httpServletRequest.getCookies();
        logger.info(".......sessionid:{},session:{}",httpServletRequest.getSession().getId(), JSONObject.toJSONString(httpServletRequest.getSession().getAttributeNames()));
        logger.info(".......,cookie:{}",JSONObject.toJSONString(httpServletRequest.getCookies()));

        logger.info("CrosXssFilter.......orignal url:{},ParameterMap:{}", httpServletRequest.getRequestURI(), JSONObject.toJSONString(httpServletRequest.getParameterMap()));
        XssHttpServletRequestWrapper xssHttpServletRequestWrapper = new XssHttpServletRequestWrapper(
                httpServletRequest);
        chain.doFilter(xssHttpServletRequestWrapper, response);
        logger.info("CrosXssFilter..........doFilter url:{},ParameterMap:{}", xssHttpServletRequestWrapper.getRequestURI(), JSONObject.toJSONString(xssHttpServletRequestWrapper.getParameterMap()));
    }

    @Override
    public void destroy() {

    }

}
