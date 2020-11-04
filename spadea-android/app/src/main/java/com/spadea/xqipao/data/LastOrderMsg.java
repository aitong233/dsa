package com.spadea.xqipao.data;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.data
 * 创建人 王欧
 * 创建时间 2020/6/6 4:27 PM
 * 描述 describe
 */
public class LastOrderMsg {
    private int orderId;
    private int type;
    private String userId;
    private String playUserId;
    private int isBoss;

    public LastOrderMsg() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPlayUserId() {
        return playUserId;
    }

    public void setPlayUserId(String playUserId) {
        this.playUserId = playUserId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getIsBoss() {
        return isBoss;
    }

    public void setIsBoss(int isBoss) {
        this.isBoss = isBoss;
    }
}
