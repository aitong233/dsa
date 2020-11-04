package com.qpyy.libcommon.bean;

public class FaceBean {


    /**
     * number : 0
     * face_spectial : https://yutangyuyin.oss-cn-hangzhou.aliyuncs.com/user-dir/N4WsWKm4pS.gif
     * pit : 9
     * type : 1
     */

    private int number;
    private String face_spectial;
    private String pit;
    private int type;

    public FaceBean(int number, int type) {
        this.number = number;
        this.type = type;
    }

    public FaceBean() {
    }

    public FaceBean(String face_spectial, int type) {
        this.face_spectial = face_spectial;
        this.type = type;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getFace_spectial() {
        return face_spectial;
    }

    public void setFace_spectial(String face_spectial) {
        this.face_spectial = face_spectial;
    }

    public String getPit() {
        return pit;
    }

    public void setPit(String pit) {
        this.pit = pit;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
