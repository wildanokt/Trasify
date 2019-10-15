package com.wildanokt.trasify.notification;

public class notificationItem {

    private int id;
    private String title;
    private String message;

    public notificationItem(int id, String title, String message) {
        this.id = id;
        this.title = title;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }
}
