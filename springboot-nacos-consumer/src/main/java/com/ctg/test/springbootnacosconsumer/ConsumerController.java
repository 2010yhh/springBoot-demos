package com.ctg.test.springbootnacosconsumer;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @Description: http://localhost:8090/springboot-nacos-consumer/get
 * @Author: yanhonghai
 * @Date: 2019/4/11 10:30
 */
@Controller
public class ConsumerController {
    @NacosInjected
    private NamingService namingService;
    private RestTemplate restTemplate = new RestTemplate();

    @RequestMapping(value = "/get")
    @ResponseBody
    public Object test(){
        Map<String,Object> map =new HashMap<>();
        map.put("username()",getUserName());
        return map;
}
    private String getUserName() {
        try {
            if (namingService != null) {
                // 选择服务的一个健康的实例（可配置负载均衡策略）
                Instance instance = namingService.selectOneHealthyInstance("UserService");
                // 拼接请求接口url并请求选取的实例
                String url = "http://" + instance.getIp() + ":" + instance.getPort() + "/springboot-nacos/config/get";
                ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
                return entity.getBody();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
