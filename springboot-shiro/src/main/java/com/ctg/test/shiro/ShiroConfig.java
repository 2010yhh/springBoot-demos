package com.ctg.test.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

import org.apache.shiro.mgt.SecurityManager;

/**
 * @Description: shiro配置
 * @Author: yanhonghai
 * @Date: 2018/9/17 0:57
 */
@Configuration
public class ShiroConfig {
    private final static Logger logger = LoggerFactory.getLogger(ShiroConfig.class);

    /**
     * ShiroFilterFactoryBean，是个factorybean，为了生成ShiroFilter。
     * 它主要保持了三项数据，securityManager，filters，filterChainDefinitionManager
     *
     * @param manager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager manager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(manager);
        //配置登录的url和登录成功的url
        //setLoginUrl 如果不设置值，默认会自动寻找Web工程根目录下的"/login.jsp"页面 或 "/login" 映射
        //实际可以在前端控制登录成功后的跳转
        bean.setLoginUrl("/index");
        bean.setSuccessUrl("/home");
        // 设置无权限时跳转的 url;
        bean.setUnauthorizedUrl("/403");
        /**
         * Shiro 内置过滤器，过滤链定义，从上向下顺序执行
         *  常用的过滤器：
         *      anon:无需认证（登录）可以访问
         *      authc:必须认证才可以访问
         *      user:只要登录过，并且记住了密码，如果设置了rememberMe的功能可以直接访问
         *      perms:该资源必须得到资源权限才可以访问
         *      role:该资源必须得到角色的权限才可以访问
         */
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

        //anon表示可以匿名访问
        filterChainDefinitionMap.put("/index", "anon");
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/user/testDb","anon");
        //authc表示需要登录
        filterChainDefinitionMap.put("/user/**","authc");
        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return bean;
    }

    /**
     * 定义安全管理器securityManager,注入自定义的realm,
     * 这个类组合了登陆，登出，权限，session的处理
     *
     * @param authRealm
     * @return
     */

    @Bean(name = "securityManager")
    public SecurityManager securityManager(@Qualifier("authRealm") Md5Realm authRealm) {

        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //设置realm
        securityManager.setRealm(authRealm);
        //用户授权/认证信息Cache, 采用EhCache缓存
        securityManager.setCacheManager(getEhCacheManager());
        //注入记住我管理器
        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }

    /**
     * 自定义的Realm，继承自AuthorizingRealm，负责用户的认证和权限的处理
     *
     * @param matcher
     * @return
     */
    @Bean
    public Md5Realm authRealm(@Qualifier("hashedCredentialsMatcher") HashedCredentialsMatcher matcher) {
        Md5Realm authRealm = new Md5Realm();
        authRealm.setCredentialsMatcher(matcher);
        authRealm.setCacheManager(getEhCacheManager());
        return authRealm;
    }

    /**
     * 配置自定义的密码比较器
     */
    @Bean
    public CredentialsMatcher credentialsMatcher() {
        return new CredentialsMatcher();
    }

    /**
     * 配置MD5密码比较器
     * 校验规则HashedCredentialsMatcher
     * 处理认证匹配处理器：如果自定义需要实现继承HashedCredentialsMatcher
     */
    @Bean("hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //指定加密方式为MD5
        credentialsMatcher.setHashAlgorithmName("MD5");
        //加密次数
        credentialsMatcher.setHashIterations(1024);
        credentialsMatcher.setStoredCredentialsHexEncoded(true);
        return credentialsMatcher;
    }

    /**
     * 1.LifecycleBeanPostProcessor，这是个DestructionAwareBeanPostProcessor的子类，
     * 负责org.apache.shiro.util.Initializable类型bean的生命周期的，初始化和销毁，主要是AuthorizingRealm类的子类，以及EhCacheManager类
     *
     * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * Spring的一个bean，由Advisor决定对哪些类的方法进行AOP代理
     *
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }

    /**
     * shiro里实现的Advisor类，配置shiro跟spring的关联
     * 内部使用AopAllianceAnnotationsAuthorizingMethodInterceptor来拦截用以下注解的方法
     *
     * @param manager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager manager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(manager);
        return advisor;
    }

    /**
     * 缓存管理，用户登陆成功后，把用户信息和权限信息缓存起来，然后每次用户请求时，
     * 放入用户的session中，如果不设置这个bean，每个请求都会查询一次数据库
     *
     * @return
     */

    @Bean
    public EhCacheManager getEhCacheManager() {
        System.out.println("11111111111ShiroConfiguration.getEhCacheManager()");
        EhCacheManager ehcacheManager = new EhCacheManager();
        ehcacheManager.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
        return ehcacheManager;
    }
    //rememberMe

    /**
     * cookie对象;
     * rememberMeCookie()方法是设置Cookie的生成模版，比如cookie的name，cookie的有效时间等等。
     *
     * @return 对重要的cookie设置httpOnly, 防止客户端通过document.cookie读取cookie。服务端可以设置此字段。
     */
    @Bean
    public SimpleCookie rememberMeCookie() {
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //<!-- 记住我cookie生效时间30天 ,单位秒;-->
        simpleCookie.setMaxAge(120);
        return simpleCookie;
    }

    /**
     * cookie管理对象;
     * rememberMeManager()方法是生成rememberMe管理器，而且要将这个rememberMe管理器设置到securityManager中
     *
     * @return
     */
    @Bean
    public CookieRememberMeManager rememberMeManager() {

        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
        cookieRememberMeManager.setCipherKey(Base64.decode("2AvVhdsgUs0FSA3SDFAdag=="));
        return cookieRememberMeManager;
    }
}