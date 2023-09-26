package com.challengers.of.call.Profiledata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WithdrawFiRequestJson {
    @SerializedName("Email")
    @Expose
    private String Email;

    @SerializedName("Login")
    @Expose
    private String Login;

    @SerializedName("Amount")
    @Expose
    private String Amount;

    @SerializedName("Mobile")
    @Expose
    private String Mobile;


    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }
}
