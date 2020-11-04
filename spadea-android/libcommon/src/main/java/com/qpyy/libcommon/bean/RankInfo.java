package com.qpyy.libcommon.bean;

import java.io.Serializable;

public class RankInfo  implements Serializable {


    /**
     * rank_id : 54
     * rank_name : 王冠
     * nobility_id : 0
     * nobility_name :
     * picture :
     */

    public int rank_id;
    public String rank_name;
    public int nobility_id;
    public String nobility_name;
    public String picture;

    public RankInfo() {

    }

    public int getRank_id() {
        return rank_id;
    }

    public void setRank_id(int rank_id) {
        this.rank_id = rank_id;
    }

    public String getRank_name() {
        return rank_name;
    }

    public void setRank_name(String rank_name) {
        this.rank_name = rank_name;
    }

    public int getNobility_id() {
        return nobility_id;
    }

    public void setNobility_id(int nobility_id) {
        this.nobility_id = nobility_id;
    }

    public String getNobility_name() {
        return nobility_name;
    }

    public void setNobility_name(String nobility_name) {
        this.nobility_name = nobility_name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }



}
