package com.ctg.test.controller;

import com.ctg.test.config.RedisUtil;
import com.ctg.test.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * //UUID.randomUUID().toString();
 * redis存储：
 * http://localhost:8090/testredis/getRedis
 * http://localhost:8090/testredis/setRedis
 * redis共享session：
 * http://localhost:8090/testredis/setSession?key=yhh&value=yhh_value
 * http://localhost:8090/testredis/getSession?key=yhh
 * http://localhost:8091/testredis1/setSession?key=yhh&value=yhh_value
 * http://localhost:8091/testredis1/getSession?key=yhh
 */
@Controller
public class TestRedisController {
    private final Logger log = LoggerFactory.getLogger(TestRedisController.class);
    @Autowired
    RedisUtil redisUtil;
    @RequestMapping(value = "/setSession", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> firstResp(HttpSession session,HttpServletRequest request, @RequestParam("key") String key
            , @RequestParam("value") String value) {
        Map<String, Object> map = new HashMap<>();
        request.getSession().setAttribute(key, value);
        map.put("session_id", session.getId());
        map.put("session_key", key);
        map.put("session_value", value);
        return map;
    }

    @RequestMapping(value = "/getSession", method = RequestMethod.GET)
    @ResponseBody
    public Object sessions(HttpSession session,HttpServletRequest request, @RequestParam("key") String key) {
        Map<String, Object> map = new HashMap<>();
        map.put("session_id", session.getId());
        map.put("session_key", key);
        map.put("session_value", session.getAttribute(key));
        return map;
    }
    @RequestMapping(value = "/setRedis", method = RequestMethod.GET)
    @ResponseBody
    public Object setRedis(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        User user = new User();
        user.setUserName("redisUtil_yhh");
        user.setPassWord("redisUtil_yhhyhh");
        //redisTemplate2.opsForValue().set("test",user);
        redisUtil.del("test");
        redisUtil.set("test", user);
        map.put("setRedis", user);
        return map;
    }

    @RequestMapping(value = "/getRedis", method = RequestMethod.GET)
    @ResponseBody
    public Object getRedis(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        //redisTemplate2.opsForValue().get("test")
        map.put("getRedis", redisUtil.get("test"));
        return map;
    }
}