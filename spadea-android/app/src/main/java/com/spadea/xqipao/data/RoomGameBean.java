package com.spadea.xqipao.data;

import java.util.List;

public class RoomGameBean {


    /**
     * gift : [{"number":47,"prize_title":"求爱"},{"number":52,"prize_title":"果汁"},{"number":1,"prize_title":"钻戒"}]
     * nickname : 因为一个人爱上一座城
     */

    private String nickname;
    private List<GiftBean> gift;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public List<GiftBean> getGift() {
        return gift;
    }

    public void setGift(List<GiftBean> gift) {
        this.gift = gift;
    }

    public static class GiftBean {
        /**
         * number : 47
         * prize_title : 求爱
         */

        private int number;
        private String prize_title;

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public String getPrize_title() {
            return prize_title;
        }

        public void setPrize_title(String prize_title) {
            this.prize_title = prize_title;
        }
    }
}
