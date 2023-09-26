package com.challengers.of.call.SocialCode;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SocialCodeData {
    @Expose
    @SerializedName("username")
    public String username;

    @SerializedName("userRFreeLife")
    @Expose
    public String userRFreeLife;

    @SerializedName("Totalwallet")
    @Expose
    public String totalwallet;



    @SerializedName("userid")
    @Expose
    public String userid;

    @SerializedName("sponsorid")
    @Expose
    public String sponsorid;

    @SerializedName("Totallife")
    @Expose
    public String Totallife;

    @SerializedName("Password")
    @Expose
    public String Password;


}
