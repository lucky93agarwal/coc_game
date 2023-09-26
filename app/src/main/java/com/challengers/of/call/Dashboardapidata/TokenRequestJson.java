package com.challengers.of.call.Dashboardapidata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TokenRequestJson {
    @SerializedName("token")
    @Expose
    private String token;

    @SerializedName("userid")
    @Expose
    private String userid;

    @SerializedName("imei")
    @Expose
    private String imei;




    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }
}
