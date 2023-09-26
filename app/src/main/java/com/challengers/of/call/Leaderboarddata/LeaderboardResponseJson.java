package com.challengers.of.call.Leaderboarddata;

import com.challengers.of.call.SocialCode.SocialCodeData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class LeaderboardResponseJson {

    @SerializedName("message")
    @Expose
    public String message;

    @Expose
    @SerializedName("data")
    public ArrayList<LeaderboardOneData> data;

    @Expose
    @SerializedName("Leaderdata")
    public ArrayList<LeaderboardTwoData> Leaderdata;
}
