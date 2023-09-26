package com.challengers.of.call.ChallengersAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChallengersGetRequestJson {
    @SerializedName("Loginid")
    @Expose
    private String Loginid;

    @SerializedName("Contestid")
    @Expose
    private String Contestid;


    @SerializedName("Gameid")
    @Expose
    private String Gameid;


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

    public String getGameid() {
        return Gameid;
    }

    public void setGameid(String gameid) {
        Gameid = gameid;
    }


}
