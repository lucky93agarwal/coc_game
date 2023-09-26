package com.challengers.of.call.Practice;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PracticeData {
    @Expose
    @SerializedName("gameid")
    public String gameid;

    @Expose
    @SerializedName("name")
    public String name;

    @Expose
    @SerializedName("icon")
    public String icon;

    @Expose
    @SerializedName("game_url")
    public String gameurl;

    @Expose
    @SerializedName("layout")
    public String layout;



}
