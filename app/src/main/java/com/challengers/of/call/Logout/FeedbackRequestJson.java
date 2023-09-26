package com.challengers.of.call.Logout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FeedbackRequestJson {

    @SerializedName("Loginid")
    @Expose
    private String Loginid;


    @SerializedName("Email")
    @Expose
    private String Email;


    @SerializedName("Name")
    @Expose
    private String Name;


    @SerializedName("Message")
    @Expose
    private String Message;


    public String getLoginid() {
        return Loginid;
    }

    public void setLoginid(String loginid) {
        Loginid = loginid;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}