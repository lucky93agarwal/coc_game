package com.challengers.of.call.SignUpData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignUpVerifyRequestJson {
    @SerializedName("Username")
    @Expose
    private String username;

    @SerializedName("Mobile")
    @Expose
    private String mobile;

    @SerializedName("Password")
    @Expose
    private String password;

    @SerializedName("Sponsorid")
    @Expose
    private String sponsorid;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSponsorid() {
        return sponsorid;
    }

    public void setSponsorid(String sponsorid) {
        this.sponsorid = sponsorid;
    }
}
