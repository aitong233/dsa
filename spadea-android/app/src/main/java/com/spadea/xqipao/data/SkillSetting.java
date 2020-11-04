package com.spadea.xqipao.data;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.data
 * 创建人 王欧
 * 创建时间 2020/5/25 9:55 AM
 * 描述 describe
 */
public class SkillSetting {

    /**
     * userId : 3
     * skillId : 1
     * skillName : null
     * price : null
     * orderSwitch : 1
     */

    private int applyId;
    private int userId;
    private int skillId;
    private String skillName;
    private String price;
    private int orderSwitch;
    private String skillUnit;

    public String getSkillUnit() {
        return skillUnit;
    }

    public void setSkillUnit(String skillUnit) {
        this.skillUnit = skillUnit;
    }

    public int getApplyId() {
        return applyId;
    }

    public void setApplyId(int applyId) {
        this.applyId = applyId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
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
