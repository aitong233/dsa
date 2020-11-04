package com.spadea.xqipao.data;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.data
 * 创建人 王欧
 * 创建时间 2020/6/8 1:41 PM
 * 描述 describe
 */
public class EvaluateModel {

    /**
     * orderId : 562
     * serveStar : 4
     * specialtyStar : 4
     * feelStar : 2
     * detail : 评价陪陪备注
     */

    private int orderId;
    private int serveStar;
    private String specialtyStar;
    private int feelStar;
    private String detail;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getServeStar() {
        return serveStar;
    }

    public void setServeStar(int serveStar) {
        this.serveStar = serveStar;
    }

    public String getSpecialtyStar() {
        return specialtyStar;
    }

    public void setSpecialtyStar(String specialtyStar) {
        this.specialtyStar = specialtyStar;
    }

    public int getFeelStar() {
        return feelStar;
    }

    public void setFeelStar(int feelStar) {
        this.feelStar = feelStar;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
