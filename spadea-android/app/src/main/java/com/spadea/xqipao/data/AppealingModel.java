package com.spadea.xqipao.data;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.data
 * 创建人 王欧
 * 创建时间 2020/6/9 1:32 PM
 * 描述 describe
 */
public class AppealingModel {

    /**
     * orderId : 624
     * userId : 642654
     * playUserId : 642673
     * playUserReason : 申诉理由
     */

    private int orderId;
    private String userId;
    private String playUserId;
    private String playUserReason;

    public AppealingModel(int orderId, String userId, String playUserId) {
        this.orderId = orderId;
        this.userId = userId;
        this.playUserId = playUserId;
    }

    public AppealingModel() {
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
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

    public String getPlayUserReason() {
        return playUserReason;
    }

    public void setPlayUserReason(String playUserReason) {
        this.playUserReason = playUserReason;
    }
}
