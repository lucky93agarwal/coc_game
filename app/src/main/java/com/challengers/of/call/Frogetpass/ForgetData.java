package com.challengers.of.call.Frogetpass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ForgetData {
    @Expose
    @SerializedName("MobileNo")
    public String mobileno;

    @SerializedName("Userid")
    @Expose
    public String userid;
}
