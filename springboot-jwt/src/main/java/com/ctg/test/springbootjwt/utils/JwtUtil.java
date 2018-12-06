package com.ctg.test.springbootjwt.utils;


import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ctg.test.springbootjwt.filter.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
/**
 * @Description: Token的生成和解析
 * @Author: yanhonghai
 * @Date: 2018/11/22 10:17
 */
public class JwtUtil {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);
    /** token秘钥, 解密用户 */
    public static final String SECRET = "admin123456";
    /** token 过期时间: 60s */
    public static final int calendarField = Calendar.SECOND;
    public static final int calendarInterval = 600;

    /**
     * JWT生成Token.<br/>
     *
     * JWT构成: header, payload, signature
     *
     *
     *
     */
    public static String createToken(User user) throws Exception {
        Date iatDate = new Date();
        // expire time
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(calendarField, calendarInterval);
        Date expiresDate = nowTime.getTime();

        // header Map
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");

        // build token
        // param backups {iss:Service, aud:APP}
        String token = JWT.create().withHeader(map) // header
                .withClaim("iss", "test jwt token") // payload
                .withClaim("aud", "webapp1")
                .withClaim("userId",user.getId())
                .withClaim("userName",user.getUserName())
                .withIssuedAt(iatDate) // sign time
                .withExpiresAt(expiresDate) // expire time
                .sign(Algorithm.HMAC256(SECRET)); // signature


        return token;
    }

    /**
     * 校验Token
     *token中的签名是由Header和有效载荷通过Base64编码生成再通过加密算法HS256和密钥最终生成签名，
     * 这个签名位于JWT的尾部，在服务器端同样对返回过来的JWT的前部分再进行一次签名生成，
     * 然后比较这次生成的签名与请求的JWT中的签名是否一致，
     * 若一致说明token合法。由于生成签名的密钥是服务器才知道的，所以别人难以伪造。
     * @param token
     * @return
     * @throws Exception
     */
    public static Map<String, Claim> verifyToken(String token) {
        DecodedJWT jwt = null;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            jwt = verifier.verify(token);
        } catch (Exception e) {
            logger.error("token 校验失败, Token已过期。");
            throw new RuntimeException("token 校验失败, Token已过期。");
        }
        logger.info("DecodedJWT:{}",JSON.toJSONString(jwt));
        return jwt.getClaims();
    }

    /**
     * 根据Token获取user_id
     *
     * @param token
     * @return user_id
     */
    public static User getUser(String token) {
        User user=new User();
        Map<String, Claim> claims = verifyToken(token);
        Claim userId = claims.get("userId");
        Claim userName = claims.get("userName");
        logger.info("getUser:{}",JSON.toJSONString(claims));
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(userId)) {
            // token 校验失败, 抛出Token验证非法异常
            logger.error("token 校验失败, Token验证非法异常");
            throw new RuntimeException("token 校验失败, Token验证非法异常");
        }
        user.setId(String.valueOf(userId));
        user.setUserName(String.valueOf(userName));
        return user;
    }
}
