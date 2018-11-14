package com.ctg.test.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;

/**
 * @Description: 过滤器实现将/MQ2_manage下的url转发至/MQ2
 * @Author: yanhonghai
 * @Date: 2018/7/26 23:36
 */
@WebFilter(filterName = "UrlFilter", urlPatterns = {"/MQ2_manager/*"})
public class UrlFilter implements Filter {
private  static String manageCntextPath="MQ2_manager";
    private  static String tenantContextPath="MQ2";
@Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpServletResponseWrapper httpResponse = new HttpServletResponseWrapper((HttpServletResponse) response);
        System.out.println(httpRequest.getRequestURI());
        String path=httpRequest.getRequestURI();
        if(path.indexOf(manageCntextPath)>=0){
            System.out.print("path:"+path+"\n");
            String replacedUrl=path.replaceFirst(manageCntextPath,tenantContextPath);
            System.out.print("replacedUrl:"+replacedUrl+"\n");
            httpRequest.getRequestDispatcher(replacedUrl).forward(request,response);
        }
        else {
            chain.doFilter(request,response);

        }
    }

    @Override
    public void destroy() {
    }

}