package com.challengers.of.call.SplashData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VersionRequestJson {
    @Expose
    @SerializedName("version")
    public String version;

    @Expose
    @SerializedName("application")
    public String application;

    @Expose
    @SerializedName("imei")
    public String imei;

    @Expose
    @SerializedName("packagename")
    public String packagename;
}
