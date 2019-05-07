package com.ctg.test.security;

import com.ctg.test.controller.ImageCodeController;
import com.ctg.test.security.Authentication.MyAuthenticationFailureHandler;
import com.ctg.test.util.ImageCode;
import com.ctg.test.util.ImageCodeException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: 验证码过滤器
 * @Author: yanhonghai
 * @Date: 2019/5/4 16:46
 */
// OncePerRequestFilter : 保证过滤器只被调用一次
@Component
public class ImageCodeFilter extends OncePerRequestFilter {
    @Autowired
    private MyAuthenticationFailureHandler authenctiationFailureHandler;

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    private Logger logger = LoggerFactory.getLogger(getClass());

    // 过滤 逻辑
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // 登陆请求
        if (StringUtils.equals("/myloginForm", request.getRequestURI())
                && StringUtils.equalsIgnoreCase(request.getMethod(), "POST")) {
            try {
                logger.info("userName:{}", request.getParameter("userName"));
                logger.info("passWord:{}", request.getParameter("passWord"));
                logger.info("rememberMe:{}", request.getParameter("rememberMe"));
                logger.info("imageCode:{}", request.getParameter("imageCode"));
                validate(new ServletWebRequest(request));
            } catch (ImageCodeException e) {
                // 有异常就返回自定义失败处理器
                authenctiationFailureHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }
        // 不是一个登录请求，不做校验 直接通过
        filterChain.doFilter(request, response);
    }

    private void validate(ServletWebRequest request) throws ServletRequestBindingException {
        ImageCode codeInSession = (ImageCode) sessionStrategy.getAttribute(request, ImageCodeController.SESSION_KEY);

        String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), "imageCode");
        if (codeInSession != null) {
            logger.info("codeInSession: {}", codeInSession.getCode());
        }
        logger.info("codeInRequest:{}", codeInRequest);
        if (codeInSession == null) {
            throw new ImageCodeException("验证码不存在");
        }
        if (StringUtils.isBlank(codeInRequest)) {
            throw new ImageCodeException("输入验证码不能为空！");
        }
        if (codeInSession.isExpire()) {
            sessionStrategy.removeAttribute(request, ImageCodeController.SESSION_KEY);
            throw new ImageCodeException("验证码已过期");
        }

        if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
            throw new ImageCodeException("验证码不匹配");
        }

        sessionStrategy.removeAttribute(request, ImageCodeController.SESSION_KEY);

    }
}
