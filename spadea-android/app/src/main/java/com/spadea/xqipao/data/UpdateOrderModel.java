package com.spadea.xqipao.data;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.data
 * 创建人 王欧
 * 创建时间 2020/6/6 4:32 PM
 * 描述 describe
 */
public class UpdateOrderModel {
    private int orderId;
    private int type;

    public UpdateOrderModel(int orderId, int type) {
        this.orderId = orderId;
        this.type = type;
    }

    public UpdateOrderModel() {
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
}
