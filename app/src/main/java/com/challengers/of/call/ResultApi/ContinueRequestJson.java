package com.challengers.of.call.ResultApi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContinueRequestJson {
    @SerializedName("Loginid")
    @Expose
    private String Loginid;

    @SerializedName("Nickname")
    @Expose
    private String Nickname;

    @SerializedName("Contestid")
    @Expose
    private String Contestid;

    @SerializedName("Newcontestid")
    @Expose
    private String Newcontestid;

    @SerializedName("Entryfees")
    @Expose
    private String Entryfees;

    @SerializedName("Amount")
    @Expose
    private String Amount;



    public String getLoginid() {
        return Loginid;
    }

    public void setLoginid(String loginid) {
        Loginid = loginid;
    }

    public String getNickname() {
        return Nickname;
    }

    public void setNickname(String nickname) {
        Nickname = nickname;
    }

    public String getContestid() {
        return Contestid;
    }

    public void setContestid(String contestid) {
        Contestid = contestid;
    }

    public String getNewcontestid() {
        return Newcontestid;
    }

    public void setNewcontestid(String newcontestid) {
        Newcontestid = newcontestid;
    }

    public String getEntryfees() {
        return Entryfees;
    }

    public void setEntryfees(String entryfees) {
        Entryfees = entryfees;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }


}
