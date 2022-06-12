package com.solutionstouch.omsaifinance.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TransactionResult {
    @SerializedName("all_collection")
  public   ArrayList<Transaction>  transaction;

    @SerializedName("emi_schedule")
    public   ArrayList<Transaction>  emiSchedule;

    @SerializedName("status")
    public String status;

    @SerializedName("message")
    public  String message;

    @SerializedName("borrower_name")
    public String borrower_name;

    @SerializedName("mobile_no")
    public String mobile_no;

    @SerializedName("vehicle_model")
    public String vehicle_model;

    @SerializedName("vehicle_model_type")
    public String vehicle_model_type;

    public ArrayList<Transaction> getTransaction() {
        return transaction;
    }

    public void setTransaction(ArrayList<Transaction> transaction) {
        this.transaction = transaction;
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

    public ArrayList<Transaction> getEmiSchedule() {
        return emiSchedule;
    }

    public void setEmiSchedule(ArrayList<Transaction> emiSchedule) {
        this.emiSchedule = emiSchedule;
    }

    public String getBorrower_name() {
        return borrower_name;
    }

    public void setBorrower_name(String borrower_name) {
        this.borrower_name = borrower_name;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getVehicle_model() {
        return vehicle_model;
    }

    public void setVehicle_model(String vehicle_model) {
        this.vehicle_model = vehicle_model;
    }

    public String getVehicle_model_type() {
        return vehicle_model_type;
    }

    public void setVehicle_model_type(String vehicle_model_type) {
        this.vehicle_model_type = vehicle_model_type;
    }
}
