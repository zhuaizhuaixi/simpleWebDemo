package com.fzu.demo.web.vo;

import java.util.Date;

/**
 * Created by zzx on 2017/11/8.
 */
public class CompanyInfo {
    private  String companyName;

    private  String adminiRegion;

    private String postcode;

    private Date createTime;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAdminiRegion() {
        return adminiRegion;
    }

    public void setAdminiRegion(String adminiRegion) {
        this.adminiRegion = adminiRegion;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "CompanyInfo{" +
                "companyName='" + companyName + '\'' +
                ", adminiRegion='" + adminiRegion + '\'' +
                ", postcode='" + postcode + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
