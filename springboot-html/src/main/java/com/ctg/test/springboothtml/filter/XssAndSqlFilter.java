package com.ctg.test.springboothtml.filter;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * ClassName: XssAndSqlFilter
 * Description: 输入类的描述
 *
 * @author YCKJ1350
 * @version 1.0.0
 * @date 2019/10/29
 */
public class XssAndSqlFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(XssAndSqlFilter.class);
    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String method = "GET";
        String param = "";
        logger.info("XssAndSqlFilter....... ParameterMap:{}",JSONObject.toJSONString(request.getParameterMap()));
        XssAndSqlHttpServletRequestWrapper xssRequest = null;
        if (request instanceof HttpServletRequest) {
            method = ((HttpServletRequest) request).getMethod();
            xssRequest = new XssAndSqlHttpServletRequestWrapper((HttpServletRequest) request);
        }
        if ("POST".equalsIgnoreCase(method)) {
            param = this.getBodyString(xssRequest.getReader());
            if(StringUtils.isNotBlank(param)){
                if(xssRequest.checkXSSAndSql(param)){
                    JSONObject result = new JSONObject();
                    result.put("code", ResultCode.PARAMETER_VERIFY_ERROR.getKey());
                    result.put("message", ResultCode.PARAMETER_VERIFY_ERROR.getValue()+":请求中有违反安全规则元素存在，拒绝访问!");
                    this.writeJson(response, result);
                    return;
                }
            }
        }
        if (xssRequest.checkParameter()) {
            JSONObject result = new JSONObject();
            result.put("message", ResultCode.PARAMETER_VERIFY_ERROR.getValue()+":请求中有违反安全规则元素存在，拒绝访问!");
            result.put("code", ResultCode.PARAMETER_VERIFY_ERROR.getKey());
            this.writeJson(response, result);
            return;
        }
        chain.doFilter(xssRequest, response);
        logger.info("XssAndSqlFilter..........doFilter url:{},ParameterMap:{}",xssRequest.getRequestURI(), JSONObject.toJSONString(xssRequest.getParameterMap()));
    }

    /**
     * 创建日期:2018年4月6日<br/>
     * 代码创建:黄聪<br/>
     * 功能描述:写数据到浏览器上<br/>
     * @param resp
     * @param json
     */
    public void writeJson(ServletResponse resp , JSONObject json ){
        PrintWriter out = null;
        try {
            //设定类容为json的格式
            resp.setContentType("application/json;charset=UTF-8");
            out = resp.getWriter();
            //写到客户端
            out.write(json.toJSONString());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(out != null){
                out.close();
            }
        }
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub

    }

    // 获取request请求body中参数
    public String getBodyString(BufferedReader br) {
        String inputLine;
        String str = "";
        try {
            while ((inputLine = br.readLine()) != null) {
                str += inputLine;
            }
            br.close();
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
        return str;

    }

}