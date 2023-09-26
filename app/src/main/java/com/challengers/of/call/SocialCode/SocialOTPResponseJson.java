package com.challengers.of.call.SocialCode;

import com.challengers.of.call.Signindata.FBLoginData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SocialOTPResponseJson {

    @SerializedName("message")
    @Expose
    public String message;

    @Expose
    @SerializedName("data")
    public ArrayList<SocialCodeData> data;
}
