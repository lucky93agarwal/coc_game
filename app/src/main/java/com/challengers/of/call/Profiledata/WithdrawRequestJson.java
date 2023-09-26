package com.challengers.of.call.Profiledata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WithdrawRequestJson {
    @SerializedName("Loginid")
    @Expose
    private String Loginid;

    @SerializedName("Amount")
    @Expose
    private String Amount;


    public String getLoginid() {
        return Loginid;
    }

    public void setLoginid(String loginid) {
        Loginid = loginid;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }
}
