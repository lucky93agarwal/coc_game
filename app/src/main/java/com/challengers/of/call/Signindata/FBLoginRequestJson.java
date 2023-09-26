package com.challengers.of.call.Signindata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FBLoginRequestJson {
    @SerializedName("Email")
    @Expose
    private String email;

    @SerializedName("Name")
    @Expose
    private String name;

    @SerializedName("Password")
    @Expose
    private String password;

    @SerializedName("imei")
    @Expose
    private String imei;

    @SerializedName("Gender")
    @Expose
    private String gender;

    @SerializedName("facebookuri")
    @Expose
    private String facebookuri;

    @SerializedName("Action")
    @Expose
    private String Action;

    @SerializedName("reg_id")
    @Expose
    private String token;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFacebookuri() {
        return facebookuri;
    }

    public void setFacebookuri(String facebookuri) {
        this.facebookuri = facebookuri;
    }

    public String getAction() {
        return Action;
    }

    public void setAction(String action) {
        Action = action;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
