package com.spadea.xqipao.data;

public class VipInfo {


    /**
     * exp :
     * current :
     * next :
     * diff :
     * nickname :
     * head_picture :
     */

    private String exp;
    private String current;
    private String next;
    private String diff;
    private String nickname;
    private String head_picture;
    private int rank_id;
    private int percent;

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public int getRank_id() {
        return rank_id;
    }

    public void setRank_id(int rank_id) {
        this.rank_id = rank_id;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getDiff() {
        return diff;
    }

    public void setDiff(String diff) {
        this.diff = diff;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHead_picture() {
        return head_picture;
    }

    public void setHead_picture(String head_picture) {
        this.head_picture = head_picture;
    }
}
