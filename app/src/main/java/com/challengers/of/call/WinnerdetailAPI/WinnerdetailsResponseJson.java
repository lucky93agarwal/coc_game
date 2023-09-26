package com.challengers.of.call.WinnerdetailAPI;

import com.challengers.of.call.Profiledata.AlldataGet;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class WinnerdetailsResponseJson {
    @SerializedName("message")
    @Expose
    public String message;

    @Expose
    @SerializedName("data")
    public ArrayList<AlldataGet> data;

}
