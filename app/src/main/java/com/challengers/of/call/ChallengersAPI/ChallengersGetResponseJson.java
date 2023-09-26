package com.challengers.of.call.ChallengersAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChallengersGetResponseJson {
    @SerializedName("message")
    @Expose
    public String Count;

    @SerializedName("Counter")
    @Expose
    public String ChallengerCounter;

    @SerializedName("TotalWallet")
    @Expose
    public String TotalWallet;

}
