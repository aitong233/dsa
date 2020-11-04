package com.qpyy.libcommon.http;

import android.os.Parcel;
import android.os.Parcelable;

public class BaseModel<T> implements Parcelable {
    private int status;
    private T data;
    private String info;

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
        dest.writeString(this.info);
    }

    protected BaseModel(Parcel in) {
        this.status = in.readInt();
        this.info = in.readString();
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
