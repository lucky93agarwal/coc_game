package com.challengers.of.call.Add_Money_Popup;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FirstRequestJson {
    @SerializedName("orderid")
    @Expose
    private String orderid;
    @SerializedName("mid")
    @Expose
    private String mid;
    @SerializedName("txntype")
    @Expose
    private String txntype;

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getTxntype() {
        return txntype;
    }

    public void setTxntype(String txntype) {
        this.txntype = txntype;
    }
}
