package com.challengers.of.call.ResetPass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResetPassRequestJson {
    @SerializedName("Userid")
    @Expose
    private String userid;

    @SerializedName("Password")
    @Expose
    private String password;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
