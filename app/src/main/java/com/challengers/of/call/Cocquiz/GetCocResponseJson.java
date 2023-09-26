package com.challengers.of.call.Cocquiz;

import com.challengers.of.call.BannedData.Banned;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GetCocResponseJson {
    @SerializedName("data")
    @Expose
    public ArrayList<CocQuestion> data;
}
