package com.challengers.of.call.ResultApi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuitRequestJson {
    @SerializedName("Loginid")
    @Expose
    private String Loginid;

    @SerializedName("Contestid")
    @Expose
    private String Contestid;

    @SerializedName("Winningamount")
    @Expose
    private String Winningamount;

    public String getLoginid() {
        return Loginid;
    }

    public void setLoginid(String loginid) {
        Loginid = loginid;
    }

    public String getContestid() {
        return Contestid;
    }

    public void setContestid(String contestid) {
        Contestid = contestid;
    }

    public String getWinningamount() {
        return Winningamount;
    }

    public void setWinningamount(String winningamount) {
        Winningamount = winningamount;
    }
}
