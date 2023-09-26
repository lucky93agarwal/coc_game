package com.challengers.of.call.Logout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LogoutRequestJson {
    @SerializedName("Loginid")
    @Expose
    private String Loginid;


    public String getLoginid() {
        return Loginid;
    }

    public void setLoginid(String loginid) {
        Loginid = loginid;
    }
}