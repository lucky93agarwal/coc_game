package com.challengers.of.call.History;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HistoryData {
    @Expose
    @SerializedName("Date")
    public String Date;

    @SerializedName("WalletType")
    @Expose
    public String WalletType;

    @SerializedName("Rupees")
    @Expose
    public String Rupees;



    @SerializedName("CreditDebit")
    @Expose
    public String CreditDebit;


}
