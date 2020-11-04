package com.spadea.xqipao.data;

import com.qpyy.libcommon.bean.RankInfo;

import java.util.List;

public class CharmModel {


    /**
     * lists : [{"user_id":"547311","number":"28881670","head_picture":"http://yutangyuyin.oss-cn-hangzhou.aliyuncs.com/android_images/547311/20191112172614_1573550774542660.jpg","nickname":"玩一玩","rank":1},{"user_id":"547627","number":"18807589","head_picture":"http://thirdwx.qlogo.cn/mmopen/vi_32/jIsRICEJOy6eEp0MLYXPMNl4Z3suHSJQepzcA6mudXiaFSxWicGZrJepA04pc5ZyNH2xczFUPx4AjibiaAHCUFvI5g/132","nickname":"鱼糖-果汁","rank":2},{"user_id":"547989","number":"6054171","head_picture":"","nickname":"阿特","rank":3},{"user_id":"547197","number":"4493043","head_picture":"https://yutangyuyin.oss-cn-hangzhou.aliyuncs.com/android_images/547197/20191109215422_1573307662745463.jpg","nickname":"言眸","rank":4},{"user_id":"547964","number":"2152096","head_picture":"","nickname":"991730","rank":5},{"user_id":"547985","number":"1485430","head_picture":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqO9bwI75jV3aC1HlLCkkf56lunkOST4onjjC92BE5E4hn3q8hB3UFMXzD2chnVAqE1Vx31rIjcFA/132","nickname":"啊啊啊啊啊啊啊啊","rank":6}]
     * my : {"head_picture":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKJq9Kq5vXanJDRNTOnMiaAVgXFqgmZ8qhxcLG4rCxdsdbKgl8E0K7JnAL4JGqPOcO2LJcSsyEPQjA/132","nickname":"因为一个人爱上一座城","user_id":"547969","number":0,"rank":-1,"diff":"1485430"}
     */

    private MyBean my;
    private List<ListsBean> lists;

    public MyBean getMy() {
        return my;
    }

    public void setMy(MyBean my) {
        this.my = my;
    }

    public List<ListsBean> getLists() {
        return lists;
    }

    public void setLists(List<ListsBean> lists) {
        this.lists = lists;
    }

    public static class MyBean {
        /**
         * head_picture : http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKJq9Kq5vXanJDRNTOnMiaAVgXFqgmZ8qhxcLG4rCxdsdbKgl8E0K7JnAL4JGqPOcO2LJcSsyEPQjA/132
         * nickname : 因为一个人爱上一座城
         * user_id : 547969
         * number : 0
         * rank : -1
         * diff : 1485430
         */

        private String head_picture;
        private String nickname;
        private String user_id;
        private String number;
        private int rank;
        private String diff;
        private String count;
        private String level;
        private RankInfo rank_info;

        public RankInfo getRank_info() {
            return rank_info;
        }

        public void setRank_info(RankInfo rank_info) {
            this.rank_info = rank_info;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
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

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }

        public String getDiff() {
            return diff;
        }

        public void setDiff(String diff) {
            this.diff = diff;
        }
    }

    public static class ListsBean {
        /**
         * user_id : 547311
         * number : 28881670
         * head_picture : http://yutangyuyin.oss-cn-hangzhou.aliyuncs.com/android_images/547311/20191112172614_1573550774542660.jpg
         * nickname : 玩一玩
         * rank : 1
         */

        private String user_id;
        private String number;
        private String head_picture;
        private String nickname;
        private int rank;
        private String count;
        private String level;
        private RankInfo rank_info;

        public RankInfo getRank_info() {
            return rank_info;
        }

        public void setRank_info(RankInfo rank_info) {
            this.rank_info = rank_info;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
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

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }
    }
}
