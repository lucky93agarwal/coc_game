package com.challengers.of.call.Add_Money_Popup;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FirstResponseJson {
    @SerializedName("message")
    @Expose
    public String message;

    @Expose
    @SerializedName("data")
    public ArrayList<FirstData> data;
}
