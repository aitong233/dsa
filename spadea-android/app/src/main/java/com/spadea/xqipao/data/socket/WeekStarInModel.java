package com.spadea.xqipao.data.socket;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.data.socket
 * 创建人 王欧
 * 创建时间 2020/4/26 11:22 AM
 * 描述 describe
 */
public class WeekStarInModel {
    private String room_id;

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public static class DataBean {
        private String nickname;
        private int type;
        private int level;

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }
    }
}
