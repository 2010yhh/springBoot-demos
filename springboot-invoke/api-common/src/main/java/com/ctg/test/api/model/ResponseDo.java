package com.ctg.test.api.model;

import java.io.Serializable;

/**
 * @Description: TODO
 * @Author: yanhonghai
 * @Date: 2018/11/3 18:19
 */
public class ResponseDo  implements Serializable{
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        switch (this.result = result) {
        }
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    String result;
    String date;
}
