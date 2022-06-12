package com.solutionstouch.omsaifinance.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class LoanResult {

    @SerializedName("data")
    ArrayList<Loan> loan;

    @SerializedName("code")
    int code;

    @SerializedName("status")
    String status;

    @SerializedName("message")
    String message;

    public void setLoan(ArrayList<Loan> loan) {
        this.loan = loan;
    }

    public ArrayList<Loan> getLoan() {
        return loan;
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
