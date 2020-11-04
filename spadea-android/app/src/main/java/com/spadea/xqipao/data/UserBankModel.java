package com.spadea.xqipao.data;

import java.io.Serializable;

public class UserBankModel {


    /**
     * user_bank : null
     * money : 49579.96400000001
     */

    private BankInfo user_bank;
    private String money;
    String alipay_account;
    String alipay_realname;

    public String getAlipay_account() {
        return alipay_account;
    }

    public void setAlipay_account(String alipay_account) {
        this.alipay_account = alipay_account;
    }

    public String getAlipay_realname() {
        return alipay_realname;
    }

    public void setAlipay_realname(String alipay_realname) {
        this.alipay_realname = alipay_realname;
    }

    public BankInfo getUser_bank() {
        return user_bank;
    }

    public void setUser_bank(BankInfo user_bank) {
        this.user_bank = user_bank;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }


    public static class BankInfo implements Serializable {
        private String id;
        private String bank_num;
        private String cardholder;
        private String bank_name;
        private String mobile;
        private String card_number;
        private String bank_num_hide;
        private String bank_zhi;
        /**
         * card_number_hide : 4305 **** **** 427X
         * cardholder_hide : 王*
         * mobile_hide : 130****7850
         */

        private String card_number_hide;
        private String cardholder_hide;
        private String mobile_hide;


        public String getBank_zhi() {
            return bank_zhi;
        }

        public void setBank_zhi(String bank_zhi) {
            this.bank_zhi = bank_zhi;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getBank_num() {
            return bank_num;
        }

        public void setBank_num(String bank_num) {
            this.bank_num = bank_num;
        }

        public String getCardholder() {
            return cardholder;
        }

        public void setCardholder(String cardholder) {
            this.cardholder = cardholder;
        }

        public String getBank_name() {
            return bank_name;
        }

        public void setBank_name(String bank_name) {
            this.bank_name = bank_name;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getCard_number() {
            return card_number;
        }

        public void setCard_number(String card_number) {
            this.card_number = card_number;
        }

        public String getBank_num_hide() {
            return bank_num_hide;
        }

        public void setBank_num_hide(String bank_num_hide) {
            this.bank_num_hide = bank_num_hide;
        }

        public String getCard_number_hide() {
            return card_number_hide;
        }

        public void setCard_number_hide(String card_number_hide) {
            this.card_number_hide = card_number_hide;
        }

        public String getCardholder_hide() {
            return cardholder_hide;
        }

        public void setCardholder_hide(String cardholder_hide) {
            this.cardholder_hide = cardholder_hide;
        }

        public String getMobile_hide() {
            return mobile_hide;
        }

        public void setMobile_hide(String mobile_hide) {
            this.mobile_hide = mobile_hide;
        }
    }
}
