package com.challengers.of.call.Banned_APP;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class bannedRequestJson {
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
