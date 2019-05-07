package com.ctg.test.security;

import com.ctg.test.security.Authentication.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * @Description: 由于security是由UsernamePasswordAuthenticationFilter这个类定义登录的,
 * 里面默认是/login路径,我们要让他用我们的/mylogin路径,就需要配置.loginProcessingUrl("/mylogin")
 * @Author: yanhonghai
 * @Date: 2019/4/13 15:48
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 配置了自定义登入登出Handler，优先响应登入登出Handler，
     * 这里是返回json给前端处理，后端重定向设置不起效果
     */
    @Autowired
    MyAuthenticationFailureHandler myAuthenticationFailureHandler;
    @Autowired
    MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Autowired
    MyLogoutSuccessHandle myLogoutSuccessHandle;
    @Autowired
    MyAccessDeniedHandler myAccessDeniedHandler;
    @Autowired
    MyAuthenticationEntryPoint myAuthenticationEntryPoint;
    @Autowired
    MyUserDetailsService myUserDetailsService;
    @Autowired
    ImageCodeFilter validateCodeFilter;
    @Autowired
    private DataSource dataSource;

    /**
     * 用户发送请求到UsernamePasswordAuthenticationFilter，
     * 当用户认证成功以后，会调一个RemeberMeService这样一个服务。
     * 这个服务里面有一个TokenRepository，会生成一个Token，将这个Token写入到浏览器的Cookie里面，
     * 同时TokenRepository把生成的Token写入到数据库里面（还有用户名）
     * springSecurity会根据情况自动将token插入persistent_logins
     * .antMatchers("/admin/**").access("hasRole('ADMIN') and hasIpAddress('123.123.123.123')") // pass SPEL using access method
     * 再次访问需要权限的资源时：用cookie中的加密串，到db中验证，如果通过，自动登录才算通过
     *
     * @return
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //将我们自定义的验证码过滤器，配置到UsernamePasswordAuthenticationFilter之前
        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class);
        http
                .authorizeRequests()
                .antMatchers("/","/index","/mylogin", "/static/**", "/createImageCode").permitAll()//定义不需要认证就可以访问
                //定义需要相应角色就可以访问，角色信息可以自定义，在sys_role表中存储
                //和在接口中使用注解同样效果hasAuthority 等同于hasRole,校验时角色将被增加 "ROLE_"
                .antMatchers( "/user/list").hasRole("admin")
                .antMatchers( "/user/list2").hasRole("manager")
                .anyRequest().authenticated()//其余所有请求都需要登录认证才能访问
                .and()
                .formLogin()
                //指定url，可由相应的controller处理跳转到登录页如login_page.html
                .loginPage("/mylogin")//自定义登录url
                //指定自定义form表单请求的路径
                .loginProcessingUrl("/myloginForm").usernameParameter("userName").passwordParameter("passWord")
                //.defaultSuccessUrl("/success")
                .successForwardUrl("/success")//设置了登入登出的Handler,优先响应Handler
                .failureUrl("/fail")//设置了登入登出的Handler,优先响应Handler
                //自定义认证成功或者失败的返回json
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailureHandler)
                //这个formLogin().permitAll()方法允许所有用户基于表单登录访问/login这个page。
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/mylogout")//自定义退出url
                .logoutSuccessUrl("/mylogin")
                .logoutSuccessHandler(myLogoutSuccessHandle)//设置了登入登出的Handler,优先响应Handler
                .invalidateHttpSession(true)
                .permitAll()
                .and()
                .rememberMe()// 记住我
                .rememberMeParameter("rememberMe")
                .tokenRepository(persistentTokenRepository())
                .userDetailsService(myUserDetailsService).tokenValiditySeconds(60 * 60 * 24);
        //默认都会产生一个hiden标签 里面有安全相关的验证 防止请求伪造 这边我们暂时不需要 可禁用掉
        http.csrf().disable();
        http.exceptionHandling().authenticationEntryPoint(myAuthenticationEntryPoint);//未登录
        http.exceptionHandling().accessDeniedHandler(myAccessDeniedHandler); // 无权访问

    }

    /**
     * 密码加密的bean
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

}