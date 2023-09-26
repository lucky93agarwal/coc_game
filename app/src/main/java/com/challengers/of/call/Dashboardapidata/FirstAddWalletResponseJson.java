package com.challengers.of.call.Dashboardapidata;

import com.challengers.of.call.SocialCode.SocialCodeData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FirstAddWalletResponseJson {
    @SerializedName("message")
    @Expose
    public String message;

    @Expose
    @SerializedName("data")
    public ArrayList<FirstWalletData> data;
}
