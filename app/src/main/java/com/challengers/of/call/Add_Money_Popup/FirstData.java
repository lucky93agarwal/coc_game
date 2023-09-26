package com.challengers.of.call.Add_Money_Popup;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FirstData {
    @Expose
    @SerializedName("TXNID")
    public String TXNID;

    @SerializedName("BANKTXNID")
    @Expose
    public String BANKTXNID;

    @SerializedName("ORDERID")
    @Expose
    public String ORDERID;



    @SerializedName("TXNAMOUNT")
    @Expose
    public String TXNAMOUNT;

    @SerializedName("STATUS")
    @Expose
    public String STATUS;

    @SerializedName("GATEWAYNAME")
    @Expose
    public String GATEWAYNAME;

    @SerializedName("RESPCODE")
    @Expose
    public String RESPCODE;


    @SerializedName("RESPMSG")
    @Expose
    public String RESPMSG;

    @SerializedName("BANKNAME")
    @Expose
    public String BANKNAME;

    @SerializedName("MID")
    @Expose
    public String MID;

    @SerializedName("PAYMENTMODE")
    @Expose
    public String PAYMENTMODE;

    @SerializedName("TXNDATE")
    @Expose
    public String TXNDATE;


}
