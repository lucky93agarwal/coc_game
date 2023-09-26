package com.challengers.of.call.BannedData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GetBannedResponseJson {

    @SerializedName("data")
    @Expose
    public ArrayList<Banned> data;
}
