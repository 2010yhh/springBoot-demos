package com.ctg.test.api.util;

import com.caucho.hessian.client.HessianProxyFactory;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

public class HessinProxyUtil {
    private static final Logger logger = LoggerFactory.getLogger(HessinProxyUtil.class);
    private static HessinProxyUtil invokerProxyHelper=null;
    private static  Map<String,Object> proxyMap=new HashMap<>();
    private HessinProxyUtil(){}
    public static HessinProxyUtil getInstance(){
        if(invokerProxyHelper==null){
            invokerProxyHelper=new HessinProxyUtil();
        }
        return invokerProxyHelper;
    }

    public <T> T doRefer(final Class<T> serviceType, final String url)throws MalformedURLException {
        try {
            if(proxyMap.get(url+"/"+serviceType.getSimpleName())==null){
                final HessianProxyFactory hessianProxyFactory = new HessianProxyFactory();
                hessianProxyFactory.setConnectTimeout(30000);
                hessianProxyFactory.setReadTimeout(30000);
                Object object=hessianProxyFactory.create(serviceType, url);
                proxyMap.put(url+"/"+serviceType.getSimpleName(),(T) object);
            }
            return (T)proxyMap.get(url+"/"+serviceType.getSimpleName());
        } catch (MalformedURLException e) {
           logger.error(ExceptionUtils.getFullStackTrace(e));
        }
        return null;
    }
}
