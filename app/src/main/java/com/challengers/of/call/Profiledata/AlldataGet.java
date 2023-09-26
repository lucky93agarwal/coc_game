package com.challengers.of.call.Profiledata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AlldataGet {
    @Expose
    @SerializedName("createdchallenge")
    public String createdchallenge;

    @SerializedName("joinedchallenge")
    @Expose
    public String joinedchallenge;

    @SerializedName("Totalpayedchallenge")
    @Expose
    public String Totalpayedchallenge;

    @SerializedName("wonchallenge")
    @Expose
    public String wonchallenge;

    @SerializedName("losechallenge")
    @Expose
    public String losechallenge;

    @SerializedName("winning")
    @Expose
    public String winning;

    @SerializedName("status")
    @Expose
    public String status;


}
