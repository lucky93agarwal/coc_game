package com.challengers.of.call.Practice;

import com.challengers.of.call.BannedData.Banned;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GetPracticeResponseJson {
    @SerializedName("data")
    @Expose
    public ArrayList<PracticeData> data;
}
