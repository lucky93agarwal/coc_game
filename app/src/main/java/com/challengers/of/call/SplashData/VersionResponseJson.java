package com.challengers.of.call.SplashData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VersionResponseJson {
    @Expose
    @SerializedName("new_version")
    public String new_version;

    @Expose
    @SerializedName("message")
    public String message;

    @Expose
    @SerializedName("data")
    public String[] data;


}
