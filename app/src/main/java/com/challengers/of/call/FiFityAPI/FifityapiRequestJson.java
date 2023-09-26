package com.challengers.of.call.FiFityAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FifityapiRequestJson {

    @SerializedName("Loginid")
    @Expose
    private String userid;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
