package com.challengers.of.call.Profiledata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WithdrawResponseJson {
    @SerializedName("Count")
    @Expose
    public String count;

    @SerializedName("Rupees")
    @Expose
    public String rupees;

    @SerializedName("ReciveMoney")
    @Expose
    public String reciveMoney;


}
