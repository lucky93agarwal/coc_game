package com.challengers.of.call.Contest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContestResponseJson {
    @SerializedName("Contestid")
    @Expose
    public String Count;

    @SerializedName("TotalWallet")
    @Expose
    public String Totalwallet;
}
