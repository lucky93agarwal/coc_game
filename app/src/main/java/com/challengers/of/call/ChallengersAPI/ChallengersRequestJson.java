package com.challengers.of.call.ChallengersAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChallengersRequestJson {
    @SerializedName("Loginid")
    @Expose
    private String Loginid;

    @SerializedName("Gameid")
    @Expose
    private String Gameid;

    public String getLoginid() {
        return Loginid;
    }

    public void setLoginid(String loginid) {
        Loginid = loginid;
    }

    public String getGameid() {
        return Gameid;
    }

    public void setGameid(String gameid) {
        Gameid = gameid;
    }
}
