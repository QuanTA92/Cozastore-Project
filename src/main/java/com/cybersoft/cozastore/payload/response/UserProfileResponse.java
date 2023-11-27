package com.cybersoft.cozastore.payload.response;

public class UserProfileResponse {

    private int userId;

    public UserProfileResponse(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
