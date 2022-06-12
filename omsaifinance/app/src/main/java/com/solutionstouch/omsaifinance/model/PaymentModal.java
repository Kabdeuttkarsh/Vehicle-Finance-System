package com.solutionstouch.omsaifinance.model;

public class PaymentModal {
    private int Installment_No;
    private String movieName;
    private int year;
    private int balance;

    public PaymentModal(int Installment_No, String movieName, int year, int balance) {
        this.Installment_No = Installment_No;
        this.movieName = movieName;
        this.year = year;
        this.balance = balance;
    }

    public int getInstallment_No() {
        return Installment_No;
    }

    public void setRank(int rank) {
        this.Installment_No = Installment_No;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) { this.balance = balance; }
}
