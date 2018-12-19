package com.ctg.test.durid.configure;

/**
 * @Description: TODO
 * @Author: yanhonghai
 * @Date: 2018/9/17 0:43
 */

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * 数据库连接池&Mybatis配置类
 *
 * @author Administrator
 */
@Configuration
public class DruidConfig {
    private static final Logger logger = LoggerFactory.getLogger(DruidConfig.class);

    @Autowired
    DruidProperty druidProperty;
    @Bean
    @Primary //在同样的DataSource中，首先使用被标注的DataSource
    public DataSource dataSource() {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(druidProperty.getUrl());
        datasource.setUsername(druidProperty.getUsername());
        datasource.setPassword(druidProperty.getPassword());
        datasource.setDriverClassName(druidProperty.getDriverClassName());
        datasource.setInitialSize(druidProperty.getInitialSize());
        datasource.setMinIdle(druidProperty.getMinIdle());
        datasource.setMaxActive(druidProperty.getMaxActive());
        datasource.setMaxWait(druidProperty.getMaxWait());
        datasource.setTimeBetweenEvictionRunsMillis(druidProperty.getTimeBetweenEvictionRunsMillis());
        datasource.setMinEvictableIdleTimeMillis(druidProperty.getTimeBetweenEvictionRunsMillis());
        datasource.setValidationQuery(druidProperty.getValidationQuery());
        datasource.setTestOnBorrow(druidProperty.isTestOnBorrow());
        datasource.setTestOnReturn(druidProperty.isTestOnBorrow());
        datasource.setPoolPreparedStatements(druidProperty.isPoolPreparedStatements());
        try {
            datasource.setFilters(druidProperty.getFilters());
        } catch (SQLException e) {
            logger.info("druid configuration initialization fail: " + ExceptionUtils.getFullStackTrace(e));
        }
        return datasource;
    }

    @Bean
    public ServletRegistrationBean statViewServlet() {
        //创建servlet注册实体
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        //设置ip白名单
        servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
        //设置ip黑名单
        servletRegistrationBean.addInitParameter("deny", "192.168.0.2");
        //设置控制台管理用户__登录用户名和密码
        servletRegistrationBean.addInitParameter("loginUsername", "root");
        servletRegistrationBean.addInitParameter("loginPassword", "root");
        //是否可以重置数据
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean statFilter() {
        //创建过滤器
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        //设置过滤器过滤路径
        filterRegistrationBean.addUrlPatterns("/*");
        //忽略过滤的形式
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
}