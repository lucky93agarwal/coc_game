package com.challengers.of.call.Profiledata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfileResponseJson {
    @SerializedName("Credit")
    @Expose
    public String cashwallet;

    @SerializedName("Win")
    @Expose
    public String winningamount;

    @SerializedName("Total")
    @Expose
    public String totalpoint;

    @SerializedName("Reward")
    @Expose
    public String reward;
}
