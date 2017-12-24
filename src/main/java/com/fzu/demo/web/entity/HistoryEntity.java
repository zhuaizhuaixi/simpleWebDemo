package com.fzu.demo.web.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author zzx
 * Created by zzx on 2017/12/20.
 */
public class HistoryEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer userID;

    private String operation;

    private Timestamp date;

    private Integer type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public HistoryEntity(Integer userID, String operation, Timestamp date, Integer type) {
        this.userID = userID;
        this.operation = operation;
        this.date = date;
        this.type = type;
    }

    public HistoryEntity() {
    }
}
