package com.ctg.test.api.service;

import com.ctg.test.api.model.ResponseDo;

import java.rmi.RemoteException;

/**
 * @Description: TODO
 * @Author: yanhonghai
 * @Date: 2018/11/3 11:53
 */
public interface TestService3 {
    public ResponseDo getResponse(String paramer) throws RemoteException;;
}

