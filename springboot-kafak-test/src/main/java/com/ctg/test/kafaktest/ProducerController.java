package com.ctg.test.kafaktest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: TODO
 * @Author: yanhonghai
 * @Date: 2019/4/16 15:20
 */
@Controller
@RequestMapping("/kafka")
public class ProducerController {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private KafkaProducerConfig kafkaProducerConfig;

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    @ResponseBody
    public Object sendKafka(final  String message, HttpServletRequest request, HttpServletResponse response) {
        Map<String,Object>map =new HashMap<>();
        try {
            logger.info("消息:{}",message);
            kafkaProducerConfig.kafkaTemplate().send("TestTopic", "key", message);
            logger.info("发送消息成功.");
            map.put("code",200);
            map.put("result","发送消息成功.");
            return map;
        } catch (Exception e) {
            logger.error("发送消息失败", e);
            map.put("code",201);
            map.put("result","发送消息失败.");
            return map;
        }
    }
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public Object test(final  String message, HttpServletRequest request, HttpServletResponse response) {
        Map<String,Object>map =new HashMap<>();
            map.put("code",200);
            map.put("result",message);
            return map;

    }
}
