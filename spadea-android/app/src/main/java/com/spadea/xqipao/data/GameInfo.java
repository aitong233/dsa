package com.spadea.xqipao.data;

import android.support.annotation.NonNull;

public class GameInfo {


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
