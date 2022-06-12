package com.solutionstouch.omsaifinance.model;

public class Colection {

    String emi_id;
    String borrower_id;
    String loan_id;
    String financer_id;
    String amount_paid;
    String due_amount;
    String payment_mode;
    String collection_completed_type;
    String narration;
    String collected_by;
    String collection_receipt_no;
    String collection_date;

    public String getEmi_id() {
        return emi_id;
    }

    public void setEmi_id(String emi_id) {
        this.emi_id = emi_id;
    }

    public String getBorrower_id() {
        return borrower_id;
    }

    public void setBorrower_id(String borrower_id) {
        this.borrower_id = borrower_id;
    }

    public String getLoan_id() {
        return loan_id;
    }

    public void setLoan_id(String loan_id) {
        this.loan_id = loan_id;
    }

    public String getFinancer_id() {
        return financer_id;
    }

    public void setFinancer_id(String financer_id) {
        this.financer_id = financer_id;
    }

    public String getAmount_paid() {
        return amount_paid;
    }

    public void setAmount_paid(String amount_paid) {
        this.amount_paid = amount_paid;
    }

    public String getDue_amount() {
        return due_amount;
    }

    public void setDue_amount(String due_amount) {
        this.due_amount = due_amount;
    }

    public String getPayment_mode() {
        return payment_mode;
    }

    public void setPayment_mode(String payment_mode) {
        this.payment_mode = payment_mode;
    }

    public String getCollection_completed_type() {
        return collection_completed_type;
    }

    public void setCollection_completed_type(String collection_completed_type) {
        this.collection_completed_type = collection_completed_type;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public String getCollected_by() {
        return collected_by;
    }

    public void setCollected_by(String collected_by) {
        this.collected_by = collected_by;
    }

    public String getCollection_receipt_no() {
        return collection_receipt_no;
    }

    public void setCollection_receipt_no(String collection_receipt_no) {
        this.collection_receipt_no = collection_receipt_no;
    }

    public String getCollection_date() {
        return collection_date;
    }

    public void setCollection_date(String collection_date) {
        this.collection_date = collection_date;
    }
}
