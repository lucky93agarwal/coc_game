package com.challengers.of.call.QuestionWisResult;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuestionWisResultRequestJson {
    @SerializedName("Loginid")
    @Expose
    private String Loginid;


    @SerializedName("Contestid")
    @Expose
    private String Contestid;


    @SerializedName("TotalFifty")
    @Expose
    private String TotalFifty;


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

    public String getTotalFifty() {
        return TotalFifty;
    }

    public void setTotalFifty(String totalFifty) {
        TotalFifty = totalFifty;
    }
}
