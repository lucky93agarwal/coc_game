package com.challengers.of.call.QuestionWisResult;

import com.challengers.of.call.Signindata.FBLoginData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class QuestionWisResultResponseJson {

    @SerializedName("message")
    @Expose
    public String message;

    @Expose
    @SerializedName("data")
    public ArrayList<GetData> data;




}
