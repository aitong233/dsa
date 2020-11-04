package com.spadea.xqipao.data;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.data
 * 创建人 王欧
 * 创建时间 2020/5/25 4:53 PM
 * 描述 describe
 */
public class SkillPriceSet {

    /**
     * id : 21
     * price : 15
     * orderSwitch : 0
     */

    private int id;
    private String price;
    private int orderSwitch;

    public SkillPriceSet() {
    }

    public SkillPriceSet(int id, String price, int orderSwitch) {
        this.id = id;
        this.price = price;
        this.orderSwitch = orderSwitch;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getOrderSwitch() {
        return orderSwitch;
    }

    public void setOrderSwitch(int orderSwitch) {
        this.orderSwitch = orderSwitch;
    }
}
