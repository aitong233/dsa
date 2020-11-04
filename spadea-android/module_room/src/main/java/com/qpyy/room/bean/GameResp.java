package com.qpyy.room.bean;

import android.support.annotation.NonNull;

public class GameResp {


    private String color;
    private int num;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @NonNull
    @Override
    public String toString() {
        return color + num;
    }
}
