package com.challengers.of.call.WinnerdetailAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WinnerdetailsRequestJson {
    @SerializedName("Loginid")
    @Expose
    private String Loginid;




    public String getLoginid() {
        return Loginid;
    }

    public void setLoginid(String loginid) {
        Loginid = loginid;
    }


}
