package com.challengers.of.call.Profiledata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfileImgRequestJson {
    @SerializedName("Loginid")
    @Expose
    private String loginid;

    @SerializedName("Imageurl")
    @Expose
    private String imageurl;


    @SerializedName("Imagebytes")
    @Expose
    private String imagebytes;


    public String getLoginid() {
        return loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getImagebytes() {
        return imagebytes;
    }

    public void setImagebytes(String imagebytes) {
        this.imagebytes = imagebytes;
    }
}
