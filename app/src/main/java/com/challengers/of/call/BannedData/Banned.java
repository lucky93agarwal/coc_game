package com.challengers.of.call.BannedData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Banned {

    @Expose
    @SerializedName("packageName")
    public String packageName;

    @Expose
    @SerializedName("gameName")
    public String gameName;
}
