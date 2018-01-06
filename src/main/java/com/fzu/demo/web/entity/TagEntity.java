package com.fzu.demo.web.entity;

import java.io.Serializable;

/**
 * @author zzx
 */
public class TagEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

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

    public TagEntity() {
    }

    public TagEntity(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        return getClass() == obj.getClass() && this.getId().equals(((TagEntity) obj).getId());
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = result * 31 + name.hashCode();
        result = result * 31 + id;
        return result;
    }
}
