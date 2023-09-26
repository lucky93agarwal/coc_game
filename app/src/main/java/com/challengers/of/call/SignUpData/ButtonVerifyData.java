package com.challengers.of.call.SignUpData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ButtonVerifyData {
    @Expose
    @SerializedName("mobile")
    public String mobile;

    @SerializedName("userid")
    @Expose
    public String userid;
}
