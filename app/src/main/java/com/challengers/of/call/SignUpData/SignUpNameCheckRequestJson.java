package com.challengers.of.call.SignUpData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignUpNameCheckRequestJson {
    @SerializedName("Name")
    @Expose
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
