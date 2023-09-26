package com.challengers.of.call.Leaderboarddata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LeaderboardRequestJson {

    @SerializedName("Loginid")
    @Expose
    private String loginid;

    @SerializedName("topObject")
    @Expose
    private String topObject;

    @SerializedName("from")
    @Expose
    private String from;


    public String getLoginid() {
        return loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }

    public String getTopObject() {
        return topObject;
    }

    public void setTopObject(String topObject) {
        this.topObject = topObject;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
