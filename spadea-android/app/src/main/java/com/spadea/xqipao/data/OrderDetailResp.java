package com.spadea.xqipao.data;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.data
 * 创建人 王欧
 * 创建时间 2020/6/8 1:40 PM
 * 描述 describe
 */
public class OrderDetailResp {

    /**
     * orderId : 578
     * userId : 642654
     * playUserId : 642673
     * applyId : 50
     * groupId : 2
     * skillId : 53
     * skillName : 123
     * skillUnit : 每1局
     * orderStatus : 3
     * orderStatusDetail : 主播拒绝接单
     * price : 100.0
     * number : 1
     * total : 100.0
     * evaluationStatus : 0
     * playEvaluationStatus : 0
     * endStatus : 3
     * orderTime : 2020-06-08 02:55:44
     * headPicture : https://yutangyuyin.oss-cn-hangzhou.aliyuncs.com/android_images/642673/20200606094022_1591407622797130.png
     * nickname : 883409
     * sex : 1
     * feelStar : null
     * serveStar : null
     * specialtyStar : null
     * scoreStar : null
     * isBoss : false
     */

    private int orderId;
    private int userId;
    private int playUserId;
    private int applyId;
    private int groupId;
    private int skillId;
    private String skillName;
    private String skillUnit;
    private int orderStatus;
    private String orderStatusDetail;
    private int price;
    private int number;
    private String total;
    private int evaluationStatus;
    private int playEvaluationStatus;
    private int endStatus;
    private String orderTime;
    private String headPicture;
    private String nickname;
    private int sex;
    private int feelStar;
    private int serveStar;
    private int specialtyStar;
    private int scoreStar;
    private int isBoss;
    private String remark;
    private String emchatUsername;
    private String detail;

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getEmchatUsername() {
        return emchatUsername;
    }

    public void setEmchatUsername(String emchatUsername) {
        this.emchatUsername = emchatUsername;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPlayUserId() {
        return playUserId;
    }

    public void setPlayUserId(int playUserId) {
        this.playUserId = playUserId;
    }

    public int getApplyId() {
        return applyId;
    }

    public void setApplyId(int applyId) {
        this.applyId = applyId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
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

    public String getSkillUnit() {
        return skillUnit;
    }

    public void setSkillUnit(String skillUnit) {
        this.skillUnit = skillUnit;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderStatusDetail() {
        return orderStatusDetail;
    }

    public void setOrderStatusDetail(String orderStatusDetail) {
        this.orderStatusDetail = orderStatusDetail;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public int getEvaluationStatus() {
        return evaluationStatus;
    }

    public void setEvaluationStatus(int evaluationStatus) {
        this.evaluationStatus = evaluationStatus;
    }

    public int getPlayEvaluationStatus() {
        return playEvaluationStatus;
    }

    public void setPlayEvaluationStatus(int playEvaluationStatus) {
        this.playEvaluationStatus = playEvaluationStatus;
    }

    public int getEndStatus() {
        return endStatus;
    }

    public void setEndStatus(int endStatus) {
        this.endStatus = endStatus;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getHeadPicture() {
        return headPicture;
    }

    public void setHeadPicture(String headPicture) {
        this.headPicture = headPicture;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getFeelStar() {
        return feelStar;
    }

    public void setFeelStar(int feelStar) {
        this.feelStar = feelStar;
    }

    public int getServeStar() {
        return serveStar;
    }

    public void setServeStar(int serveStar) {
        this.serveStar = serveStar;
    }

    public int getSpecialtyStar() {
        return specialtyStar;
    }

    public void setSpecialtyStar(int specialtyStar) {
        this.specialtyStar = specialtyStar;
    }

    public int getScoreStar() {
        return scoreStar;
    }

    public void setScoreStar(int scoreStar) {
        this.scoreStar = scoreStar;
    }

    public int getIsBoss() {
        return isBoss;
    }

    public void setIsBoss(int isBoss) {
        this.isBoss = isBoss;
    }
}
