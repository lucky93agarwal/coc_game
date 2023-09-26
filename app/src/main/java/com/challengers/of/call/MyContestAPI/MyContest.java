package com.challengers.of.call.MyContestAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyContest {
    @Expose
    @SerializedName("Contestid")
    public String Contestid;

    @SerializedName("Contestname")
    @Expose
    public String Contestname;

    @SerializedName("Entryfees")
    @Expose
    public String Entryfees;



    @SerializedName("Winningamount")
    @Expose
    public String Winningamount;

    @SerializedName("Expiry")
    @Expose
    public String Expiry;

    @SerializedName("Status")
    @Expose
    public String Status;

    @SerializedName("Chasing")
    @Expose
    public String Chasing;


    @SerializedName("Count")
    @Expose
    public String Count;

    @SerializedName("Result")
    @Expose
    public String Result;

    @SerializedName("refund")
    @Expose
    public String refund;

    @SerializedName("game_img")
    @Expose
    public String gameimg;

    @SerializedName("game_name")
    @Expose
    public String gamename;

}
