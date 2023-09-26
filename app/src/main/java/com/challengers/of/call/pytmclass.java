package com.challengers.of.call;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.UUID;

public class pytmclass {
    @Expose
    @SerializedName("MID")
    private String mId;
    @Expose
    @SerializedName("ORDER_ID")
    private String orderId;
    @Expose
    @SerializedName("CUST_ID")
    private String custId;
    @Expose
    @SerializedName("CHANNEL_ID")
    private String channelId;
    @Expose
    @SerializedName("TXN_AMOUNT")
    private String txnAmount;
    @Expose
    @SerializedName("WEBSITE")
    private String website;
    @Expose
    @SerializedName("CALLBACK_URL")
    private String callBackUrl;
    @Expose
    @SerializedName("INDUSTRY_TYPE_ID")
    private String industryTypeId;


    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getTxnAmount() {
        return txnAmount;
    }

    public void setTxnAmount(String txnAmount) {
        this.txnAmount = txnAmount;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getCallBackUrl() {
        return callBackUrl;
    }

    public void setCallBackUrl(String callBackUrl) {
        this.callBackUrl = callBackUrl;
    }

    public String getIndustryTypeId() {
        return industryTypeId;
    }

    public void setIndustryTypeId(String industryTypeId) {
        this.industryTypeId = industryTypeId;
    }
}
