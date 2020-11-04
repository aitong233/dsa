package com.qpyy.room.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Copyright (c) 1
 *
 * @Description
 * @Author lsy
 * @Copyright Copyright (c) 1
 * @Date ${DATA} 10:26
 */
public class WxPayModel {


    /**
     * appid : wx97f7796397d8182a
     * noncestr : mUmgz026QTZ6B6uYCqqZukbM3HdsS6YE
     * package : Sign=WXPay
     * partnerid : 1521424831
     * prepayid : wx1920035641713904b39477471988096136
     * timestamp : 1555675436
     * sign : FAA41A84EFD0F804942DDFFC383D0203
     */

    private String appid;
    private String noncestr;
    @SerializedName("package")
    private String packageX;
    private String partnerid;
    private String prepayid;
    private long timestamp;
    private String sign;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getPackageX() {
        return packageX;
    }

    public void setPackageX(String packageX) {
        this.packageX = packageX;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
