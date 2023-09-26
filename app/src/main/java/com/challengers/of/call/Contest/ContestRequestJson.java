package com.challengers.of.call.Contest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContestRequestJson {
    @SerializedName("Loginid")
    @Expose
    private String Loginid;

    @SerializedName("Nickname")
    @Expose
    private String Nickname;

    @SerializedName("Entryfees")
    @Expose
    private String Entryfees;

    @SerializedName("Winningamount")
    @Expose
    private String Winningamount;


    @SerializedName("Gameid")
    @Expose
    private String Gameid;




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

    public String getGameid() {
        return Gameid;
    }

    public void setGameid(String gameid) {
        Gameid = gameid;
    }


}
