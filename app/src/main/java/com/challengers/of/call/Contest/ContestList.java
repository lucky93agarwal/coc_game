package com.challengers.of.call.Contest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContestList {
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


    @SerializedName("Winnerid")
    @Expose
    public String Winnerid;

    @SerializedName("Challengerimage")
    @Expose
    public String Challengerimage;

    @SerializedName("Challengername")
    @Expose
    public String Challengername;

    @SerializedName("Challengerid")
    @Expose
    public String Challengerid;


}
