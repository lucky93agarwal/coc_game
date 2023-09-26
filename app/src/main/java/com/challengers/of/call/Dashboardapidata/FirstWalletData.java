package com.challengers.of.call.Dashboardapidata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FirstWalletData {
    @Expose
    @SerializedName("oners")
    public String oners;

    @SerializedName("sharewin")
    @Expose
    public String sharewin;

    @SerializedName("oneptotal")
    @Expose
    public String oneptotal;



    @SerializedName("twors")
    @Expose
    public String twors;

    @SerializedName("twoptotal")
    @Expose
    public String twoptotal;

    @SerializedName("threers")
    @Expose
    public String threers;

    @SerializedName("threeptotal")
    @Expose
    public String threeptotal;


    @SerializedName("fourrs")
    @Expose
    public String fourrs;

    @SerializedName("fourptotal")
    @Expose
    public String fourptotal;

    @SerializedName("status")
    @Expose
    public String status;
}
