package com.qpyy.libcommon.bean;

import java.util.List;

public class RoomGiftModel {


    /**
     * room_id : 3040
     * list : [{"text":"<font color='#FFFFFF'>用户574376<\/font><font color='#FD8469'>在 我的房间 房间<\/font><font color='#FFFFFF'>送给了天天启哦哦<\/font><font color='#FABA5C'>百年之约<\/font><font color='#FFFFFF'>X1<\/font>","gift_picture":"http://yutangyuyin.oss-cn-hangzhou.aliyuncs.com/admin_images/5e903c5602d09.png"}]
     */

    private String room_id;
    private List<ListBean> list;

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * text : <font color='#FFFFFF'>用户574376</font><font color='#FD8469'>在 我的房间 房间</font><font color='#FFFFFF'>送给了天天启哦哦</font><font color='#FABA5C'>百年之约</font><font color='#FFFFFF'>X1</font>
         * gift_picture : http://yutangyuyin.oss-cn-hangzhou.aliyuncs.com/admin_images/5e903c5602d09.png
         */

        private String text;
        private String gift_picture;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getGift_picture() {
            return gift_picture;
        }

        public void setGift_picture(String gift_picture) {
            this.gift_picture = gift_picture;
        }
    }
}
