package com.challengers.of.call.Cocquiz;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AnswerRequestJson {
    @SerializedName("Loginid")
    @Expose
    private String Loginid;


    @SerializedName("Contestid")
    @Expose
    private String Contestid;


    @SerializedName("Questionid")
    @Expose
    private String Questionid;


    @SerializedName("Rightanswer")
    @Expose
    private String Rightanswer;

    @SerializedName("Answer")
    @Expose
    private String Answer;


    @SerializedName("Point")
    @Expose
    private String Point;


    @SerializedName("Counter")
    @Expose
    private String Counter;



    @SerializedName("Size")
    @Expose
    private String Size;


    @SerializedName("TotalLifes")
    @Expose
    private String TotalLifes;


    public String getLoginid() {
        return Loginid;
    }

    public void setLoginid(String loginid) {
        Loginid = loginid;
    }

    public String getContestid() {
        return Contestid;
    }

    public void setContestid(String contestid) {
        Contestid = contestid;
    }

    public String getQuestionid() {
        return Questionid;
    }

    public void setQuestionid(String questionid) {
        Questionid = questionid;
    }

    public String getRightanswer() {
        return Rightanswer;
    }

    public void setRightanswer(String rightanswer) {
        Rightanswer = rightanswer;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String answer) {
        Answer = answer;
    }

    public String getPoint() {
        return Point;
    }

    public void setPoint(String point) {
        Point = point;
    }

    public String getCounter() {
        return Counter;
    }

    public void setCounter(String counter) {
        Counter = counter;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String size) {
        Size = size;
    }

    public String getTotalLifes() {
        return TotalLifes;
    }

    public void setTotalLifes(String totalLifes) {
        TotalLifes = totalLifes;
    }
}
