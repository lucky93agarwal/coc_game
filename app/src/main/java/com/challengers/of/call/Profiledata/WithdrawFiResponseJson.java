package com.challengers.of.call.Profiledata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WithdrawFiResponseJson {
    @SerializedName("type")
    @Expose
    public String type;

    @SerializedName("requestGuid")
    @Expose
    public String requestGuid;


    @SerializedName("orderId")
    @Expose
    public String orderId;

    @SerializedName("status")
    @Expose
    public String status;

    @SerializedName("statusCode")
    @Expose
    public String statusCode;

    @SerializedName("statusMessage")
    @Expose
    public String statusMessage;
}
