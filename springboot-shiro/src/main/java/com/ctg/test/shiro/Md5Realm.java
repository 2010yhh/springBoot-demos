package com.ctg.test.shiro;

import com.alibaba.fastjson.JSONObject;
import com.ctg.test.model.Permission;
import com.ctg.test.model.Role;
import com.ctg.test.model.User;
import com.ctg.test.service.impl.UserServiceImpl;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Description: 自定义Realm，认证用户，授权
 * @Author: yanhonghai
 * @Date: 2018/9/17 0:56
 */
public class Md5Realm extends AuthorizingRealm {
    private final static Logger logger = LoggerFactory.getLogger(Md5Realm.class);
    /**
     * 延迟加载bean,解决缓存Cache不能正常使用;事务Transaction注解不能正常运行
     */
    @Autowired
    @Lazy
    private UserServiceImpl userService;

    /**
     * 认证.登录
     * doGetAuthenticationInfo这个方法是在用户登录的时候调用的
     * 也就是执行SecurityUtils.getSubject().login()的时候调用；(即:登录验证)
     * 验证通过后会用户保存在缓存中的
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        logger.info("##################执行Shiro登录认证##################");
        //获取用户输入的token
        UsernamePasswordToken utoken = (UsernamePasswordToken) token;
        String username = utoken.getUsername();
        //查询数据库
        User user = userService.findByUserName(username);
        //放入shiro.调用CredentialsMatcher检验密码
        if (user != null) {
            // 若存在，将此用户存放到登录认证info中，无需自己做密码对比，Shiro会为我们进行密码对比校验
            // return new SimpleAuthenticationInfo(user,user.getPassWord(),this.getClass().getName());
            //加SALT，这里的参数要给个唯一的;
            ByteSource credentialsSalt = ByteSource.Util.bytes(user.getUserName());
            //参数realmName: 当前 realm对象的name.调用父类的getName()方法即可
            return new SimpleAuthenticationInfo(user, user.getPassWord(), credentialsSalt, this.getClass().getName());
        }
        return null;
    }

    /**
     * 授权
     * doGetAuthorizationInfo方法是在我们调用
     * SecurityUtils.getSubject().isPermitted（）这个方法，
     * 授权后用户角色及权限会保存在缓存中的
     *
     * @param principal
     * @return
     * @RequiresPermissions这个注解起始就是在执行SecurityUtils.getSubject().isPermitted（）
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        //获取session中的用户，以下3种都可以
        // User user=(User) principal.getPrimaryPrincipal();
        //String userName=(String) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        User user = (User) principal.fromRealm(this.getClass().getName()).iterator().next();
        //查询数据库
        user = userService.findUserInfo(user.getUserName());
        logger.info("##################执行Shiro权限授权##################user info is：{}" + JSONObject.toJSONString(user));
        Set<String> userPermissions = new HashSet<String>();
        Set<String> userRoles = new HashSet<String>();
        for (Role role : user.getRoles()) {
            userRoles.add(role.getRoleName());
            List<Permission> rolePermissions = role.getPermissions();
            for (Permission permission : rolePermissions) {
                userPermissions.add(permission.getPermName());
            }
        }
        //角色名集合
        info.setRoles(userRoles);
        //权限名集合,将权限放入shiro中,
        // 这里可以把url，按钮，菜单，api等当做资源来进行权限控制，从而对用户进行权限控制
        info.addStringPermissions(userPermissions);
        return info;
    }
}