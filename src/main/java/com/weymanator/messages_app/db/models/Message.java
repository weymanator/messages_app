package com.weymanator.messages_app.db.models;

import java.text.SimpleDateFormat;

public class Message {
    public String ID;
    public Integer Date;
    public User Sender;
    public String Message;

    public Message() {}

    public Message(String id, Integer date, User sender, String message) {
        ID = id;
        Date = date;
        Sender = sender;
        Message = message;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setDate(Integer date) {
        Date = date;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public void setSender(User sender) {
        Sender = sender;
    }

    public java.util.Date getRawDate() {
        return new java.util.Date(Date * 1000L);
    }

    public String getDate() {
        return new SimpleDateFormat("MM/dd").format((Date * 1000L));
    }

    public String getID() {
        return ID;
    }

    public String getMessage() {
        return Message;
    }

    public User getSender() {
        return Sender;
    }
}
