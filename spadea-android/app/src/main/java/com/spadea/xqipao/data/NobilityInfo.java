package com.spadea.xqipao.data;

import java.util.List;

public class NobilityInfo {


    /**
     * info : {"nobility_id":"1","expired_time":"2020-02-03","state":"1","nobility_name":"子爵","nobility_picture":"","nickname":"因为一个人爱上一座城","head_picture":"https://yutangyuyin.oss-cn-hangzhou.aliyuncs.com/android_images/550089/20200102140031_1577944830794966.jpg"}
     * renew : [{"day":30,"price":200},{"day":90,"price":600},{"day":180,"price":1200}]
     */

    private InfoBean info;
    private List<RenewBean> renew;

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public List<RenewBean> getRenew() {
        return renew;
    }

    public void setRenew(List<RenewBean> renew) {
        this.renew = renew;
    }

    public static class InfoBean {
        /**
         * nobility_id : 1
         * expired_time : 2020-02-03
         * state : 1
         * nobility_name : 子爵
         * nobility_picture :
         * nickname : 因为一个人爱上一座城
         * head_picture : https://yutangyuyin.oss-cn-hangzhou.aliyuncs.com/android_images/550089/20200102140031_1577944830794966.jpg
         */

        private int nobility_id;
        private String expired_time;
        private String state;
        private String nobility_name;
        private String nobility_picture;
        private String nickname;
        private String head_picture;

        public int getNobility_id() {
            return nobility_id;
        }

        public void setNobility_id(int nobility_id) {
            this.nobility_id = nobility_id;
        }

        public String getExpired_time() {
            return expired_time;
        }

        public void setExpired_time(String expired_time) {
            this.expired_time = expired_time;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getNobility_name() {
            return nobility_name;
        }

        public void setNobility_name(String nobility_name) {
            this.nobility_name = nobility_name;
        }

        public String getNobility_picture() {
            return nobility_picture;
        }

        public void setNobility_picture(String nobility_picture) {
            this.nobility_picture = nobility_picture;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getHead_picture() {
            return head_picture;
        }

        public void setHead_picture(String head_picture) {
            this.head_picture = head_picture;
        }
    }

    public static class RenewBean {
        /**
         * day : 30
         * price : 200
         */

        private String day;
        private String price;
        private String rebate;
        private String nobility_picture;

        public String getNobility_picture() {
            return nobility_picture;
        }

        public void setNobility_picture(String nobility_picture) {
            this.nobility_picture = nobility_picture;
        }

        public String getRebate() {
            return rebate;
        }

        public void setRebate(String rebate) {
            this.rebate = rebate;
        }

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }
}
