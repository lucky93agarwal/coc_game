package com.challengers.of.call.Signindata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MenualData {
    
    @Expose
    @SerializedName("username")
    public String usernames;

    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("email")
    @Expose
    public String email;

    @SerializedName("userRFreeLife")
    @Expose
    public String userRFreeLife;

    @SerializedName("role")
    @Expose
    public String role;

    @SerializedName("gender")
    @Expose
    public String gender;

    @SerializedName("dateofbirth")
    @Expose
    public String dateofbirth;

    @SerializedName("sponsorid")
    @Expose
    public String sponsorid;


    @SerializedName("playstatus")
    @Expose
    public String playstatus;

    @SerializedName("Totalcontest")
    @Expose
    public String Totalcontest;

    @SerializedName("Imageurl")
    @Expose
    public String Imageurl;

    @SerializedName("Totalwallet")
    @Expose
    public String Totalwallet;

    @SerializedName("Totallife")
    @Expose
    public String Totallife;

    @SerializedName("userid")
    @Expose
    public String userid;



}
