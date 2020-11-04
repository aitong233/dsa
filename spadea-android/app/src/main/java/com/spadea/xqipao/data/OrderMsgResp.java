package com.spadea.xqipao.data;

import java.util.List;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.data
 * 创建人 王欧
 * 创建时间 2020/6/6 4:37 PM
 * 描述 describe
 */
public class OrderMsgResp {
    /**
     * records : [{"id":566,"userId":642647,"playUserId":642656,"applyId":47,"groupId":1,"skillId":53,"skillName":"123","skillUnit":"每1局","orderStatus":15,"price":3,"number":1,"total":3,"evaluationStatus":0,"playEvaluationStatus":0,"endStatus":3,"orderTime":"2020-06-17 04:33:29","headPicture":"https://yutangyuyin.oss-cn-hangzhou.aliyuncs.com/android_images/642647/20200601124239_1590986559504973.JPEG","nickname":"516612","sex":1,"feelStar":null,"serveStar":null,"specialtyStar":null,"type":null}]
     * total : 1
     * size : 10
     * current : 1
     * orders : []
     * hitCount : true
     * searchCount : true
     * pages : 1
     */

    private int total;
    private int size;
    private int current;
    private boolean hitCount;
    private boolean searchCount;
    private int pages;
    private List<RecordsBean> records;
    private List<?> orders;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public boolean isHitCount() {
        return hitCount;
    }

    public void setHitCount(boolean hitCount) {
        this.hitCount = hitCount;
    }

    public boolean isSearchCount() {
        return searchCount;
    }

    public void setSearchCount(boolean searchCount) {
        this.searchCount = searchCount;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<RecordsBean> getRecords() {
        return records;
    }

    public void setRecords(List<RecordsBean> records) {
        this.records = records;
    }

    public List<?> getOrders() {
        return orders;
    }

    public void setOrders(List<?> orders) {
        this.orders = orders;
    }

    public static class RecordsBean {
        /**
         * id : 566
         * userId : 642647
         * playUserId : 642656
         * applyId : 47
         * groupId : 1
         * skillId : 53
         * skillName : 123
         * skillUnit : 每1局
         * orderStatus : 15
         * price : 3.0
         * number : 1
         * total : 3.0
         * evaluationStatus : 0
         * playEvaluationStatus : 0
         * endStatus : 3
         * orderTime : 2020-06-17 04:33:29
         * headPicture : https://yutangyuyin.oss-cn-hangzhou.aliyuncs.com/android_images/642647/20200601124239_1590986559504973.JPEG
         * nickname : 516612
         * sex : 1
         * feelStar : null
         * serveStar : null
         * specialtyStar : null
         * type : null
         */

        private int id;
        private int userId;
        private int playUserId;
        private int applyId;
        private int groupId;
        private int skillId;
        private String skillName;
        private String skillUnit;
        private int orderStatus;
        private int price;
        private int number;
        private String total;
        private int evaluationStatus;
        private int playEvaluationStatus;
        private int endStatus;
        private int scoreStar;
        private String orderTime;
        private String headPicture;
        private String nickname;
        private int sex;
        private int feelStar;
        private int serveStar;
        private int specialtyStar;
        private int type;
        private String orderStatusDetail;

        private String emchatUsername;
        private String remark;
        private String detail;
        private String userDesc;
        private String playUserDesc;

        public String getUserDesc() {
            return userDesc;
        }

        public void setUserDesc(String userDesc) {
            this.userDesc = userDesc;
        }

        public String getPlayUserDesc() {
            return playUserDesc;
        }

        public void setPlayUserDesc(String playUserDesc) {
            this.playUserDesc = playUserDesc;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getEmchatUsername() {
            return emchatUsername;
        }

        public void setEmchatUsername(String emchatUsername) {
            this.emchatUsername = emchatUsername;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getOrderStatusDetail() {
            return orderStatusDetail;
        }

        public void setOrderStatusDetail(String orderStatusDetail) {
            this.orderStatusDetail = orderStatusDetail;
        }

        public int getScoreStar() {
            return scoreStar;
        }

        public void setScoreStar(int scoreStar) {
            this.scoreStar = scoreStar;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
