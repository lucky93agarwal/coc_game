package com.challengers.of.call.ResultApi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultData {
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


    @SerializedName("Result")
    @Expose
    public String Result;

    @SerializedName("Winnername")
    @Expose
    public String Winnername;

    @SerializedName("Winnerscore")
    @Expose
    public String Winnerscore;

    @SerializedName("Newcontest")
    @Expose
    public String Newcontest;

    @SerializedName("Winnerid")
    @Expose
    public String Winnerid;

    @SerializedName("Loosername")
    @Expose
    public String Loosername;

    @SerializedName("Looserpoint")
    @Expose
    public String Looserpoint;

    @SerializedName("Winnerimage")
    @Expose
    public String Winnerimage;

    @SerializedName("Looserimage")
    @Expose
    public String Looserimage;

    @SerializedName("Looserid")
    @Expose
    public String Looserid;


}
