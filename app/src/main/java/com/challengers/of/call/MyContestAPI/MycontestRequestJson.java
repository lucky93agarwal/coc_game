package com.challengers.of.call.MyContestAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MycontestRequestJson {
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
