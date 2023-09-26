package com.challengers.of.call.Leaderboarddata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LeaderboardOneData {
    @Expose
    @SerializedName("Sno")
    public String sno;

    @SerializedName("Contestname")
    @Expose
    public String contestname;

    @SerializedName("Winningamount")
    @Expose
    public String winningamount;

    @SerializedName("userid")
    @Expose
    public String userid;


    @SerializedName("Imageurl")
    @Expose
    public String imageurl;


    @SerializedName("Check")
    @Expose
    public String check;

    @SerializedName("prize")
    @Expose
    public String prize;
}
