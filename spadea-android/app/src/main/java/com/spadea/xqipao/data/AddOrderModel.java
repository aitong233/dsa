package com.spadea.xqipao.data;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.data
 * 创建人 王欧
 * 创建时间 2020/6/4 2:38 PM
 * 描述 describe
 */
public class AddOrderModel {


    /**
     * skillId : 34
     * number : 2
     * remarks : 备注
     * serviceTime : 2020-06-04T20:02:07
     * applyId : 44
     */

    private int number;
    private String remarks;
    private String serviceTime;
    private int applyId;


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(String serviceTime) {
        this.serviceTime = serviceTime;
    }

    public int getApplyId() {
        return applyId;
    }

    public void setApplyId(int applyId) {
        this.applyId = applyId;
    }
}
