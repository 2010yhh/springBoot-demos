package com.ctg.test.api.controller;


import com.ctg.test.api.util.HttpInvokerProxyUtil;
import com.ctg.test.api.util.RmiProxyUtil;
import com.ctg.test.api.model.ResponseDo;
import com.ctg.test.api.service.TestService;
import com.ctg.test.api.service.TestService2;
import com.ctg.test.api.service.TestService3;
import com.ctg.test.api.util.HessinProxyUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**测试api:
 *  http://localhost:8762/client/testRmi?name=222
 * http://localhost:8762/client/testHttpinvoke?name=222
 *  http://localhost:8762/client/testHessian?name=222
 */
@Controller
public class TestController {
    @RequestMapping("/testHttpinvoke")
    @ResponseBody
    public ResponseDo testHttpinvoke(String name)throws RemoteException
    {
        String url="http://127.0.0.1:8761/api/testService";
        ResponseDo responseDo=new ResponseDo();
        responseDo.setResult("service is null");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowdayTime = dateFormat.format(new Date());
        responseDo.setDate(nowdayTime);
        TestService testService= HttpInvokerProxyUtil.getInstance().doRefer(TestService.class,url);
        if(testService!=null){
            responseDo= testService.getResponse(name);
        }
        return responseDo;
    }
    @RequestMapping("/testRmi")
    @ResponseBody
    public ResponseDo testRmi(String name)throws RemoteException
    {
        String url="rmi://127.0.0.1:8760/testService2";
        ResponseDo responseDo=new ResponseDo();
        responseDo.setResult("service is null");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowdayTime = dateFormat.format(new Date());
        responseDo.setDate(nowdayTime);
        TestService2 testService2= RmiProxyUtil.getInstance().doRefer(TestService2.class,url);
        if(testService2!=null){
            responseDo= testService2.getResponse(name);
        }
        return responseDo;
    }

    @RequestMapping("/testHessian")
    @ResponseBody
   public ResponseDo testHessian(String name)throws RemoteException,MalformedURLException
    {
        String url = "http://localhost:8761/api/testService3";
        ResponseDo responseDo=new ResponseDo();
        responseDo.setResult("service is null");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowdayTime = dateFormat.format(new Date());
        responseDo.setDate(nowdayTime);
        TestService3 testService3 =HessinProxyUtil.getInstance().doRefer(TestService3.class,url);
        if(testService3!=null){
            responseDo= testService3.getResponse(name);
        }
        return responseDo;
    }

}