package com.solutionstouch.omsaifinance.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DocumentResult {

    @SerializedName("data")
    ArrayList<Documents> documents;

    @SerializedName("code")
    int code;

    @SerializedName("status")
    String status;

    @SerializedName("message")
    String message;

    public ArrayList<Documents> getDocuments() {
        return documents;
    }

    public void setDocuments(ArrayList<Documents> documents) {
        this.documents = documents;
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
