package com.fzu.demo.web.entity;

/**
 * Created by zzx on 2017/12/7.
 */

import java.io.Serializable;
import java.util.Comparator;
import java.util.HashMap;

/**
 * @author zzx
 */
public class GameEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private String description;

    private String image;

    private double price;

    private String tags;

    private Double recommendIndex;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Double getRecommendIndex() {
        return recommendIndex;
    }

    public void setRecommendIndex(Double recommendIndex) {
        this.recommendIndex = recommendIndex;
    }

    public GameEntity() {
    }

    public GameEntity(String name, String description, String image, double price) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
    }

    public GameEntity(Integer id, String name, String description, String image, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
    }
}
