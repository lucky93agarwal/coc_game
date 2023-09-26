package com.challengers.of.call.SignUpData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignUpBtnCheckRequestJson {

    @SerializedName("Sponsorid")
    @Expose
    private String sponsorid;

    @SerializedName("Mobile")
    @Expose
    private String mobile;

    @SerializedName("Username")
    @Expose
    private String name;

    @SerializedName("Password")
    @Expose
    private String password;


    @SerializedName("imei")
    @Expose
    private String imei;


    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getSponsorid() {
        return sponsorid;
    }

    public void setSponsorid(String sponsorid) {
        this.sponsorid = sponsorid;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
