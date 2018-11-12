package com.ctg.test.filter;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

/** 
 * @Description: filter的作用：
 * 拦截请求，对请求或响应(Request、Response)统一设置，过滤掉非法url，做网关转发等
 */
//配置多个路径：@WebFilter(filterName="TestFilter",urlPatterns={"/test1","/test2"})
@WebFilter(urlPatterns = "/test2/*", filterName = "indexFilter1")
@Order(Integer.MAX_VALUE-1)
public class MyFilter1 implements Filter {
	 protected Logger log = LoggerFactory.getLogger(MyFilter1.class);  
	@Override
	  public void destroy() {
		log.info("----->:indexFilter1 destroy method");
	  }
	  @Override
	  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	      throws IOException, ServletException {
		  chain.doFilter(request, response);
		  log.info("----->:indexFilter1 doFilter method");
	  }
	  @Override
	  public void init(FilterConfig arg0) throws ServletException {
		  log.info("----->:indexFilter1 init method");
	  }
}
