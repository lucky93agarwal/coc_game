package com.challengers.of.call.FiFityAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FifitybuyiRequestJson {
    @SerializedName("Loginid")
    @Expose
    private String userid;

    @SerializedName("object")
    @Expose
    private String object;


    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }
}
