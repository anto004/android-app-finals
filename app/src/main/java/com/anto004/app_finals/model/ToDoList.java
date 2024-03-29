package com.anto004.app_finals.model;

public class ToDoList {
    private long id;
    private String title;
    private String details;
    private String additionalInfo;
    private String dueDate;

    public ToDoList(String title, String details, String additionalInfo, String dueDate) {
        this.title = title;
        this.details = details;
        this.additionalInfo = additionalInfo;
        this.dueDate = dueDate;
    }

    public ToDoList(){

    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ToDoList{" +
                "title='" + title + '\'' +
                ", details='" + details + '\'' +
                ", additionalInfo='" + additionalInfo + '\'' +
                ", dueDate='" + dueDate + '\'' +
                '}';
    }
}
