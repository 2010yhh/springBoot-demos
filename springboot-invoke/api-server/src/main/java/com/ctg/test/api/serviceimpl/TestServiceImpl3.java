package com.ctg.test.api.serviceimpl;

import com.ctg.test.api.util.IpUtil;
import com.ctg.test.api.model.ResponseDo;
import com.ctg.test.api.service.TestService3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description: TODO
 * @Author: yanhonghai
 * @Date: 2018/10/27 16:43
 */
@Service
public class TestServiceImpl3 implements TestService3 {
    @Autowired
    private Environment env;
    @Autowired
    IpUtil ipUtil;
    @Override
    public ResponseDo getResponse(String name) {
        ResponseDo responseDo=new ResponseDo();
        String ip="";
        try {
            InetAddress inetAddress=ipUtil.getLocalHostLANAddress();
            ip=inetAddress.getHostAddress();
        } catch (Exception e) {
            e.printStackTrace();
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowdayTime = dateFormat.format(new Date());
        responseDo.setDate(nowdayTime);
        responseDo.setResult("From "+ip+":"+env.getProperty("server.port")+",hessisn:testService3 response:hello: "+name);
        return responseDo; }
}
