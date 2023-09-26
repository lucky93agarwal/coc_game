package com.challengers.of.call.Dashboardapidata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FirstAddWalletRequestJson {

    @SerializedName("Userid")
    @Expose
    private String loginid;

    public String getLoginid() {
        return loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }
}
