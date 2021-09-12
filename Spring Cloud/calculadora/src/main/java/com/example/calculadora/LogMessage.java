package com.example.calculadora;

import java.util.Date;

public class LogMessage {
    private String userName;
    private Date date;
    private String result;
    private String operation;
    public LogMessage(){}
    public LogMessage(String userName, Date date, String result,String operation) {
        this.userName = userName;
        this.date = date;
        this.result = result;
        this.operation = operation;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
