package com.fzu.demo.web.entity;

/**
 * Created by zzx on 2017/12/7.
 */

import java.io.Serializable;

/**
 * @author zzx
 */
public class GameEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer ID;

    private String name;

    private String description;

    private double price;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
