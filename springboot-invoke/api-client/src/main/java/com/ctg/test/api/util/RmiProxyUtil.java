package com.ctg.test.api.util;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

public class RmiProxyUtil {
    private static final Logger logger = LoggerFactory.getLogger(RmiProxyUtil.class);
    private static RmiProxyUtil invokerProxyHelper=null;
    private static  Map<String,Object> proxyMap=new HashMap<>();
    private RmiProxyUtil(){}
    public static RmiProxyUtil getInstance(){
        if(invokerProxyHelper==null){
            invokerProxyHelper=new RmiProxyUtil();
        }
        return invokerProxyHelper;
    }

    public <T> T doRefer(final Class<T> serviceType, final String url)throws RemoteException {
        try {
            if(proxyMap.get(url+"/"+serviceType.getSimpleName())==null){
                final  RmiProxyFactoryBean rmiProxyFactoryBean=new RmiProxyFactoryBean();
                rmiProxyFactoryBean.setServiceInterface(serviceType);
                rmiProxyFactoryBean.setServiceUrl(url);
                rmiProxyFactoryBean.setLookupStubOnStartup(false);
                rmiProxyFactoryBean.setRefreshStubOnConnectFailure(true);
                rmiProxyFactoryBean.afterPropertiesSet();
                rmiProxyFactoryBean.afterPropertiesSet();
                proxyMap.put(url+"/"+serviceType.getSimpleName(),(T) rmiProxyFactoryBean.getObject());
            }
            return (T)proxyMap.get(url+"/"+serviceType.getSimpleName());
        } catch (Exception e) {
            logger.error(ExceptionUtils.getFullStackTrace(e));
        }
        return null;
    }
}
