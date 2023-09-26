package com.challengers.of.call.data;

/**
 * Created by DELL on 10/14/2016.
 */
public class Getprductdata {
    private  String Winningamount;
    private String Entryfees;
    private String Points;
    private String Chase;

    public String getNickname() {
        return Nickname;
    }

    public String getChasing() {
        return Chasing;
    }

    public void setNickname(String nickname) {
        Nickname = nickname;
    }

    public void setChasing(String chasing) {
        Chasing = chasing;
    }

    private String Nickname;
    private String Chasing;

    public Getprductdata( String winningamount, String entryfees,String points,String chase,String nickname,String chasing) {
        this.setWinningamount(winningamount);
        this.setEntryfees(entryfees);
        this.setPoints(points);
        this.setChase(chase);
        this.setNickname(nickname);
        this.setChasing(chasing);

    }
    public String getWinningamount() {
        return Winningamount;
    }
    public void setWinningamount(String winningamount) {
        Winningamount = winningamount;
    }

    public String getEntryfees() {
        return Entryfees;
    }
    public void setEntryfees(String entryfees) {
        Entryfees = entryfees;
    }

    public String getPoints() {
        return Points;
    }
    public void setPoints(String points) {
        Points = points;
    }

    public String getChase() {
        return Chase;
    }
    public void setChase(String chase) {
        Chase = chase;
    }



}
