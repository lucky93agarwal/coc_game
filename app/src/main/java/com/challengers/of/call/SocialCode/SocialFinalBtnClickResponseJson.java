package com.challengers.of.call.SocialCode;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SocialFinalBtnClickResponseJson {

    @SerializedName("message")
    @Expose
    public String message;

    @SerializedName("Mobilecheck")
    @Expose
    public String mbilecheck;

    @SerializedName("Usernamecheck")
    @Expose
    public String usernamecheck;
}
