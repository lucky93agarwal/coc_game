package com.challengers.of.call.History;

import com.challengers.of.call.Signindata.FBLoginData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class HistoryResponseJson {
    @SerializedName("message")
    @Expose
    public String message;

    @Expose
    @SerializedName("data")
    public ArrayList<HistoryData> data;
}
