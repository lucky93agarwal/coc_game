package com.challengers.of.call.SocialCode;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SocialMobileCheckRequestJson {

    @SerializedName("Mobile")
    @Expose
    private String mobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
