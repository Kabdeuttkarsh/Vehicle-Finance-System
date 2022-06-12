package com.solutionstouch.omsaifinance.model;

public class Documents {

    String document_id;
    String document_type_id;
    String user_id;
    String document_number_1;
    String document_number_2;
    String front;
    String back;
    String is_active;
    String is_deleted;
    String created_on;
    String updated_on;

    public String getDocument_id() {
        return document_id;
    }

    public void setDocument_id(String document_id) {
        this.document_id = document_id;
    }

    public String getDocument_type_id() {
        return document_type_id;
    }

    public void setDocument_type_id(String document_type_id) {
        this.document_type_id = document_type_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getDocument_number_1() {
        return document_number_1;
    }

    public void setDocument_number_1(String document_number_1) {
        this.document_number_1 = document_number_1;
    }

    public String getDocument_number_2() {
        return document_number_2;
    }

    public void setDocument_number_2(String document_number_2) {
        this.document_number_2 = document_number_2;
    }

    public String getFront() {
        return front;
    }

    public void setFront(String front) {
        this.front = front;
    }

    public String getBack() {
        return back;
    }

    public void setBack(String back) {
        this.back = back;
    }

    public String getIs_active() {
        return is_active;
    }

    public void setIs_active(String is_active) {
        this.is_active = is_active;
    }

    public String getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(String is_deleted) {
        this.is_deleted = is_deleted;
    }

    public String getCreated_on() {
        return created_on;
    }

    public void setCreated_on(String created_on) {
        this.created_on = created_on;
    }

    public String getUpdated_on() {
        return updated_on;
    }

    public void setUpdated_on(String updated_on) {
        this.updated_on = updated_on;
    }
}
