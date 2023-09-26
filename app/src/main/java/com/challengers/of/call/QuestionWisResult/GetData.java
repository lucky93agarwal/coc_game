package com.challengers.of.call.QuestionWisResult;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetData {

    @SerializedName("Questionno")
    @Expose
    public String Questionno;

    @SerializedName("Answer")
    @Expose
    public String Answer;

    @SerializedName("Points")
    @Expose
    public String Points;

    @SerializedName("Time")
    @Expose
    public String Time;

    @SerializedName("key")
    @Expose
    public String key;

    @SerializedName("Totalpoint")
    @Expose
    public String Totalpoint;


    @SerializedName("TotalFifty")
    @Expose
    public String TotalFifty;
}
