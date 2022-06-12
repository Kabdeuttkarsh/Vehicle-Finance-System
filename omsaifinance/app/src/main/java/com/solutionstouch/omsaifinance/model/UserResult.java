package com.solutionstouch.omsaifinance.model;

import com.google.gson.annotations.SerializedName;

public class UserResult {

    @SerializedName("data")
    User user;

    @SerializedName("code")
    int code;

    @SerializedName("status")
    String status;

    @SerializedName("message")
    String message;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
