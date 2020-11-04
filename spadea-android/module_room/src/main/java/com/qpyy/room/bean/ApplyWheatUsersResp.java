package com.qpyy.room.bean;

import java.util.List;

public class ApplyWheatUsersResp {
    private String total;
    private List<ListData> list;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<ListData> getList() {
        return list;
    }

    public void setList(List<ListData> list) {
        this.list = list;
    }

    public static class ListData{
        private String id;
        private String user_id;
        private String pit_number;
        private String head_picture;
        private String nickname;
        private String sex;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getPit_number() {
            return pit_number;
        }

        public void setPit_number(String pit_number) {
            this.pit_number = pit_number;
        }

        public String getHead_picture() {
            return head_picture;
        }

        public void setHead_picture(String head_picture) {
            this.head_picture = head_picture;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }
    }

}
