package com.challengers.of.call.Profiledata;

import com.challengers.of.call.Signindata.MenualData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ProfiledataResponseJson {
    @SerializedName("message")
    @Expose
    public String message;

    @Expose
    @SerializedName("data")
    public ArrayList<AlldataGet> data;
}
