package com.challengers.of.call.UpdateProfile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateProfileResponseJson {
    @SerializedName("DOB")
    @Expose
    public String DOB;

    @SerializedName("Gender")
    @Expose
    public String Gender;


}
