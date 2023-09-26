package com.challengers.of.call.UpdateProfile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdatePasswordRequestJson {

    @SerializedName("Loginid")
    @Expose
    private String Loginid;

    @SerializedName("Newpassword")
    @Expose
    private String Newpassword;

    @SerializedName("Oldpassword")
    @Expose
    private String Oldpassword;

    public String getLoginid() {
        return Loginid;
    }

    public void setLoginid(String loginid) {
        Loginid = loginid;
    }

    public String getNewpassword() {
        return Newpassword;
    }

    public void setNewpassword(String newpassword) {
        Newpassword = newpassword;
    }

    public String getOldpassword() {
        return Oldpassword;
    }

    public void setOldpassword(String oldpassword) {
        Oldpassword = oldpassword;
    }
}
