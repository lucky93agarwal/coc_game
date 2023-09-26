package com.challengers.of.call.Cocquiz;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CocQuestion {
    @Expose
    @SerializedName("Questionid")
    public String Questionid;

    @Expose
    @SerializedName("Question")
    public String Question;


    @Expose
    @SerializedName("Optiona")
    public String Optiona;

    @Expose
    @SerializedName("Optionb")
    public String Optionb;


    @Expose
    @SerializedName("Optionc")
    public String Optionc;

    @Expose
    @SerializedName("Optiond")
    public String Optiond;

    @Expose
    @SerializedName("Answer")
    public String Answer;


}
