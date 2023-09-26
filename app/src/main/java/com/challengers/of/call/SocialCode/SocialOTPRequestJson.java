package com.challengers.of.call.SocialCode;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SocialOTPRequestJson {
    @SerializedName("Password")
    @Expose
    private String socialPassword;

    @SerializedName("Action")
    @Expose
    private String socialAction;

    @SerializedName("Email")
    @Expose
    private String socialEmail;

    @SerializedName("Gender")
    @Expose
    private String socialGender;

    @SerializedName("Username")
    @Expose
    private String username;

    @SerializedName("Mobile")
    @Expose
    private String userMobile;

    @SerializedName("Sponsorid")
    @Expose
    private String userSponsorid;

    @SerializedName("ImageURL")
    @Expose
    private String imageURL;

    @SerializedName("Token")
    @Expose
    private String token;
    @SerializedName("imei")
    @Expose
    private String imei;


    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getSocialPassword() {
        return socialPassword;
    }

    public void setSocialPassword(String socialPassword) {
        this.socialPassword = socialPassword;
    }

    public String getSocialAction() {
        return socialAction;
    }

    public void setSocialAction(String socialAction) {
        this.socialAction = socialAction;
    }

    public String getSocialEmail() {
        return socialEmail;
    }

    public void setSocialEmail(String socialEmail) {
        this.socialEmail = socialEmail;
    }



    public String getSocialGender() {
        return socialGender;
    }

    public void setSocialGender(String socialGender) {
        this.socialGender = socialGender;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getUserSponsorid() {
        return userSponsorid;
    }

    public void setUserSponsorid(String userSponsorid) {
        this.userSponsorid = userSponsorid;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
