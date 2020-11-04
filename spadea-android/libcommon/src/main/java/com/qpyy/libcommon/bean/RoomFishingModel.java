package com.qpyy.libcommon.bean;

public class RoomFishingModel {

    /**
     * text : <font color='#FFFFFF'>哇塞</font><font color='#FD8469'>用户574376</font><font color='#FFFFFF'>在小猫钓鱼中获得</font><font color='#FABA5C'>海豚池</font><font color='#FFFFFF'>X50</font>
     * picture : https://yutangyuyin.oss-cn-hangzhou.aliyuncs.com/user-dir/jeBPs66SRB.png
     */

    private String text;
    private String picture;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "RoomFishingModel{" +
                "text='" + text + '\'' +
                ", picture='" + picture + '\'' +
                '}';
    }
}
