package com.qpyy.room.bean;

import java.util.List;

public class CardResultBean {

    private List<CardsBean> cards;

    public List<CardsBean> getCards() {
        return cards;
    }

    public void setCards(List<CardsBean> cards) {
        this.cards = cards;
    }

    public static class CardsBean {
        /**
         * cardtype : 1-4中随机（代表扑克牌花色）
         * cardcode : 从A、2、3、4、5、6、7、8、9、10、J、Q、K 中随机(代表牌号)
         */

        private String cardtype;
        private String cardcode;

        public String getCardtype() {
            return cardtype;
        }

        public void setCardtype(String cardtype) {
            this.cardtype = cardtype;
        }

        public String getCardcode() {
            return cardcode;
        }

        public void setCardcode(String cardcode) {
            this.cardcode = cardcode;
        }
    }
}
