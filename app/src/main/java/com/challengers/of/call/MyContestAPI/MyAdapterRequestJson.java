package com.challengers.of.call.MyContestAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyAdapterRequestJson {
    @SerializedName("Loginid")
    @Expose
    private String Loginid;

    @SerializedName("Contestid")
    @Expose
    private String Contestid;

    @SerializedName("Entryfees")
    @Expose
    private String Entryfees;

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

    public String getEntryfees() {
        return Entryfees;
    }

    public void setEntryfees(String entryfees) {
        Entryfees = entryfees;
    }

    public String getWinningamount() {
        return Winningamount;
    }

    public void setWinningamount(String winningamount) {
        Winningamount = winningamount;
    }


}
