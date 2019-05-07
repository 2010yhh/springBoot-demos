package com.ctg.test.model;

import java.io.Serializable;

public class Permission implements Serializable {
    private Integer permId;

    private String permName;

    public Integer getPermId() {
        return permId;
    }

    public void setPermId(Integer permId) {
        this.permId = permId;
    }

    public String getPermName() {
        return permName;
    }

    public void setPermName(String permName) {
        this.permName = permName == null ? null : permName.trim();
    }
}