package com.challengers.of.call.Profiledata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WithdrawFinalRequestJson {
    @SerializedName("Mobile")
    @Expose
    private String Mobile;

    @SerializedName("Amount")
    @Expose
    private String Amount;

    @SerializedName("Loginid")
    @Expose
    private String Loginid;

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getLoginid() {
        return Loginid;
    }

    public void setLoginid(String loginid) {
        Loginid = loginid;
    }

}
