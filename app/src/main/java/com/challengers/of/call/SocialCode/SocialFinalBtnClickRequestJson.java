package com.challengers.of.call.SocialCode;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SocialFinalBtnClickRequestJson {

    @SerializedName("refcode")
    @Expose
    private String refcode;

    @SerializedName("Mobile")
    @Expose
    private String mobile;

    @SerializedName("Username")
    @Expose
    private String username;


    public String getRefcode() {
        return refcode;
    }

    public void setRefcode(String refcode) {
        this.refcode = refcode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
