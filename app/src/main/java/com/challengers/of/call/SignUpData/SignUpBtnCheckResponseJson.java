package com.challengers.of.call.SignUpData;

import com.challengers.of.call.Signindata.MenualData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SignUpBtnCheckResponseJson {
    @SerializedName("message")
    @Expose
    public String message;

    @Expose
    @SerializedName("CheckMobileNo")
    public String checkmobileno;

    @SerializedName("CheckUserName")
    @Expose
    public String checkusername;
}
