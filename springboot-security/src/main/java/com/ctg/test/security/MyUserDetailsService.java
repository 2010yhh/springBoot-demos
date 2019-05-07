package com.ctg.test.security;

import com.alibaba.fastjson.JSONObject;
import com.ctg.test.model.Role;
import com.ctg.test.service.UserService;
import com.ctg.test.util.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description: 自定义UserDetailsService
 * @Author: yanhonghai
 * @Date: 2019/4/12 14:44
 */

/**
 * 1)执行登录的过程中，这个方法将根据用户名去查找用户，
 * 如果用户不存在，则抛出UsernameNotFoundException异常,验证成功便返回用户所属的UserDetails信息
 * 2) UserDetails中定义了用户的账户、密码、权限等信息，可通过实现该接口中的方式自行定义用户信息类
 * UserDetails代表用户信息，即主体，相当于Shiro中的Subject。org.springframework.security.core.userdetails.User是它的一个实现。
 * 3)执行登录的过程中：security内部会校验(check方法)输入的密码和查询得到的用户的密码
 */

@Component
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private LoginEventService loginAttemptService;
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("用户的用户名: {}", username);
        if (loginAttemptService.isBlocked(username)) {
            throw  new BizException(201,String.format("username:{%s} login fail to limited,is locked",username));
        }

        /**
         * 返回spring Security自带的UserDetails
         *  创建2个内存中的用户名为:username,密码为：123456的用户；
         *  security内部会校验check输入的密码和查询得到的用户的密码
         * 角色不一样来测试权限问题
         * */
         /*User userDetails;
       if (username.equals("admin")) {
            userDetails = new User(username, passwordEncoder.encode("123456"),
                    AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_admin"));

        } else {
            userDetails = new User(username, passwordEncoder.encode("123456"),
                    AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_user"));

        }*/

        /**
         *  查询db获取用户角色及权限
         *  db中的用户密码已用BCryptPasswordEncoder加密
         */
        com.ctg.test.model.User definedUser = userService.findUserInfo(username);
        if (definedUser == null) {
            logger.error("Username :" + username + ",not found");
            throw new UsernameNotFoundException("Username :" + username + ",not found");
        }
        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        Set<Role> roles = new HashSet<>(definedUser.getRoles());
        if (roles != null) {
            for (Role role : roles) {
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role.getRoleName());
                authorities.add(authority);
            }
        }
        /*Set<String> userPermissions = new HashSet<String>();
        for (Role role : definedUser.getRoles()) {
            List<Permission> rolePermissions = role.getPermissions();
            for (Permission permission : rolePermissions) {
                userPermissions.add(permission.getPermName());
            }
        }*/
        User userDetails = new User(definedUser.getUserName(), definedUser.getPassWord(), authorities);
        logger.info("登录成功,用户信息: {}", JSONObject.toJSONString(userDetails));
        return userDetails;
    }
}