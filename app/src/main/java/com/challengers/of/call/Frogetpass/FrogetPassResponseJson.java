package com.challengers.of.call.Frogetpass;

import com.challengers.of.call.SignUpData.ButtonData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FrogetPassResponseJson {
    @SerializedName("message")
    @Expose
    public String message;

    @Expose
    @SerializedName("checkmobileno")
    public String checkmobileno;

    @SerializedName("userid")
    @Expose
    public String userid;
}
