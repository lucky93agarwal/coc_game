package com.challengers.of.call.Signindata;

import com.challengers.of.call.BannedData.Banned;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class LoginResponseJson {
    @SerializedName("message")
    @Expose
    public String message;

    @Expose
    @SerializedName("data")
    public ArrayList<MenualData> data;



}
