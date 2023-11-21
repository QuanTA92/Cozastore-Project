package com.cybersoft.cozastore.payload.response;

import java.util.Date;

public class StatusResponse {

    private String nameStatus;

    private Date createDate;

    public String getNameStatus() {
        return nameStatus;
    }

    public void setNameStatus(String nameStatus) {
        this.nameStatus = nameStatus;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
