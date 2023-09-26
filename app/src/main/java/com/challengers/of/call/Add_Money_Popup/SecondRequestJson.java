package com.challengers.of.call.Add_Money_Popup;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SecondRequestJson {
    @SerializedName("TXNID")
    @Expose
    private String TXNID;

    @SerializedName("BANKTXNID")
    @Expose
    private String BANKTXNID;

    @SerializedName("ORDERID")
    @Expose
    private String ORDERID;


    @SerializedName("TXNAMOUNT")
    @Expose
    private String TXNAMOUNT;

    @SerializedName("STATUS")
    @Expose
    private String STATUS;

    @SerializedName("TXNTYPE")
    @Expose
    private String TXNTYPE;


    @SerializedName("GATEWAYNAME")
    @Expose
    private String GATEWAYNAME;

    @SerializedName("RESPCODE")
    @Expose
    private String RESPCODE;

    @SerializedName("RESPMSG")
    @Expose
    private String RESPMSG;


    @SerializedName("BANKNAME")
    @Expose
    private String BANKNAME;

    @SerializedName("MID")
    @Expose
    private String MID;

    @SerializedName("PAYMENTMODE")
    @Expose
    private String PAYMENTMODE;



    @SerializedName("REFUNDAMT")
    @Expose
    private String REFUNDAMT;

    @SerializedName("TXNDATE")
    @Expose
    private String TXNDATE;

    @SerializedName("custID")
    @Expose
    private String custID;


    public String getTXNID() {
        return TXNID;
    }

    public void setTXNID(String TXNID) {
        this.TXNID = TXNID;
    }

    public String getBANKTXNID() {
        return BANKTXNID;
    }

    public void setBANKTXNID(String BANKTXNID) {
        this.BANKTXNID = BANKTXNID;
    }

    public String getORDERID() {
        return ORDERID;
    }

    public void setORDERID(String ORDERID) {
        this.ORDERID = ORDERID;
    }

    public String getTXNAMOUNT() {
        return TXNAMOUNT;
    }

    public void setTXNAMOUNT(String TXNAMOUNT) {
        this.TXNAMOUNT = TXNAMOUNT;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getTXNTYPE() {
        return TXNTYPE;
    }

    public void setTXNTYPE(String TXNTYPE) {
        this.TXNTYPE = TXNTYPE;
    }

    public String getGATEWAYNAME() {
        return GATEWAYNAME;
    }

    public void setGATEWAYNAME(String GATEWAYNAME) {
        this.GATEWAYNAME = GATEWAYNAME;
    }

    public String getRESPCODE() {
        return RESPCODE;
    }

    public void setRESPCODE(String RESPCODE) {
        this.RESPCODE = RESPCODE;
    }

    public String getRESPMSG() {
        return RESPMSG;
    }

    public void setRESPMSG(String RESPMSG) {
        this.RESPMSG = RESPMSG;
    }

    public String getBANKNAME() {
        return BANKNAME;
    }

    public void setBANKNAME(String BANKNAME) {
        this.BANKNAME = BANKNAME;
    }

    public String getMID() {
        return MID;
    }

    public void setMID(String MID) {
        this.MID = MID;
    }

    public String getPAYMENTMODE() {
        return PAYMENTMODE;
    }

    public void setPAYMENTMODE(String PAYMENTMODE) {
        this.PAYMENTMODE = PAYMENTMODE;
    }

    public String getREFUNDAMT() {
        return REFUNDAMT;
    }

    public void setREFUNDAMT(String REFUNDAMT) {
        this.REFUNDAMT = REFUNDAMT;
    }

    public String getTXNDATE() {
        return TXNDATE;
    }

    public void setTXNDATE(String TXNDATE) {
        this.TXNDATE = TXNDATE;
    }

    public String getCustID() {
        return custID;
    }

    public void setCustID(String custID) {
        this.custID = custID;
    }
}
