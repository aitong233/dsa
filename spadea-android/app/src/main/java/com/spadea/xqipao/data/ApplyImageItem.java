package com.spadea.xqipao.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.data
 * 创建人 王欧
 * 创建时间 2020/5/20 10:21 AM
 * 描述 describe
 */
public class ApplyImageItem implements Parcelable {
    public String url;
    public String localPath;

    public ApplyImageItem(String url, String localPath) {
        this.url = url;
        this.localPath = localPath;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeString(this.localPath);
    }

    protected ApplyImageItem(Parcel in) {
        this.url = in.readString();
        this.localPath = in.readString();
    }

    public static final Parcelable.Creator<ApplyImageItem> CREATOR = new Parcelable.Creator<ApplyImageItem>() {
        @Override
        public ApplyImageItem createFromParcel(Parcel source) {
            return new ApplyImageItem(source);
        }

        @Override
        public ApplyImageItem[] newArray(int size) {
            return new ApplyImageItem[size];
        }
    };
}
