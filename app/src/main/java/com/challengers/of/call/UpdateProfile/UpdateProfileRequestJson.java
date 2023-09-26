package com.challengers.of.call.UpdateProfile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateProfileRequestJson {
    @SerializedName("Loginid")
    @Expose
    private String Loginid;

    @SerializedName("DOB")
    @Expose
    private String DOB;

    @SerializedName("Gender")
    @Expose
    private String Gender;

    public String getLoginid() {
        return Loginid;
    }

    public void setLoginid(String loginid) {
        Loginid = loginid;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }
}
