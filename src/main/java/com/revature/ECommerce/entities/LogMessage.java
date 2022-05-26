package com.revature.ECommerce.entities;

import javax.persistence.*;

@Entity
public class LogMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String message;

    @Column(length = 18000)
    private String trace;

    @Column
    private String timestamp;


    public LogMessage() {
    }

    public LogMessage(String message, String trace, String timestamp) {
        this.message = message;
        this.trace = trace;
        this.timestamp = timestamp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTrace() {
        return trace;
    }

    public void setTrace(String trace) {
        this.trace = trace;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String date) {
        this.timestamp = date;
    }
}
