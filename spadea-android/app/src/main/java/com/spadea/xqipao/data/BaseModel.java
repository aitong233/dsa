package com.spadea.xqipao.data;

import android.os.Parcel;
import android.os.Parcelable;

public class BaseModel<T> implements Parcelable {
    private int status;
    private int code;
    private T data;
    private String info;
    private String msg;
    boolean isJava;

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isJava() {
        return isJava;
    }

    public void setJava(boolean java) {
        isJava = java;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public BaseModel() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.status);
        dest.writeInt(this.code);
        dest.writeString(this.info);
        dest.writeString(this.msg);
    }

    protected BaseModel(Parcel in) {
        this.status = in.readInt();
        this.code = in.readInt();
        this.info = in.readString();
        this.msg = in.readString();
    }

    public static final Creator<BaseModel> CREATOR = new Creator<BaseModel>() {
        @Override
        public BaseModel createFromParcel(Parcel source) {
            return new BaseModel(source);
        }

        @Override
        public BaseModel[] newArray(int size) {
            return new BaseModel[size];
        }
    };
}
