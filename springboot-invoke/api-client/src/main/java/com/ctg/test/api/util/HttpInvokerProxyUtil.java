package com.ctg.test.api.util;

import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;
import org.springframework.remoting.httpinvoker.SimpleHttpInvokerRequestExecutor;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

public class HttpInvokerProxyUtil {
    private static HttpInvokerProxyUtil invokerProxyHelper=null;
    private static  Map<String,Object> proxyMap=new HashMap<>();
    private HttpInvokerProxyUtil(){}
    public static HttpInvokerProxyUtil getInstance(){
        if(invokerProxyHelper==null){
            invokerProxyHelper=new HttpInvokerProxyUtil();
        }
        return invokerProxyHelper;
    }

    public <T> T doRefer(final Class<T> serviceType, final String url){
            if(proxyMap.get(url+"/"+serviceType.getSimpleName())==null){
                final HttpInvokerProxyFactoryBean httpProxyFactoryBean = new HttpInvokerProxyFactoryBean();
                httpProxyFactoryBean.setServiceUrl(url);
                httpProxyFactoryBean.setServiceInterface(serviceType);
                SimpleHttpInvokerRequestExecutor httpInvokerRequestExecutor = new SimpleHttpInvokerRequestExecutor() {
                   @Override
                    protected void prepareConnection(HttpURLConnection con,
                                                     int contentLength) throws IOException {
                        super.prepareConnection(con, contentLength);
                        con.setReadTimeout(30000);
                        con.setConnectTimeout(30000);
                    }
                };
                httpProxyFactoryBean.setHttpInvokerRequestExecutor(httpInvokerRequestExecutor);
                httpProxyFactoryBean.afterPropertiesSet();
                proxyMap.put(url+"/"+serviceType.getSimpleName(),(T) httpProxyFactoryBean.getObject());
            }
            return (T)proxyMap.get(url+"/"+serviceType.getSimpleName());
}}
