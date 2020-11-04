package com.spadea.xqipao.data;

import com.qpyy.libcommon.bean.RankInfo;

public class RoomUserBean {


    /**
     * banned : 0
     * favorite : 0
     * pit : 0
     * voice : 0
     * shutup : 0
     */

    private int banned;
    private int favorite;
    private int pit;
    private int voice;
    private int shutup;
    private RankInfo rank_info;
    private int show_cat;

    public int getShow_cat() {
        return show_cat;
    }

    public void setShow_cat(int show_cat) {
        this.show_cat = show_cat;
    }

    public RankInfo getRank_info() {
        return rank_info;
    }

    public void setRank_info(RankInfo rank_info) {
        this.rank_info = rank_info;
    }

    public int getBanned() {
        return banned;
    }

    public void setBanned(int banned) {
        this.banned = banned;
    }

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }

    public int getPit() {
        return pit;
    }

    public void setPit(int pit) {
        this.pit = pit;
    }

    public int getVoice() {
        return voice;
    }

    public void setVoice(int voice) {
        this.voice = voice;
    }

    public int getShutup() {
        return shutup;
    }

    public void setShutup(int shutup) {
        this.shutup = shutup;
    }
}
