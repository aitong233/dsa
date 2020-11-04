package com.spadea.xqipao.data;

import java.util.List;

public class EarningsModel {
    private String earnings;
    private List<EarningInfo> list;

    public String getEarnings() {
        return earnings;
    }

    public void setEarnings(String earnings) {
        this.earnings = earnings;
    }

    public List<EarningInfo> getList() {
        return list;
    }

    public void setList(List<EarningInfo> list) {
        this.list = list;
    }

    /**
     *   {
     *                     "id": "",
     *                     "user_money": "",
     *                     "change_time": "",
     *                     "change_type": "",
     *                     "change_desc": "",
     *                     "content": ""
     *                 }
     */
    public static class EarningInfo{
        private String id;
        private String user_money;
        private String change_time;
        private String change_type;
        private String change_desc;
        private String content;
        private int symbol;


        public int getSymbol() {
            return symbol;
        }

        public void setSymbol(int symbol) {
            this.symbol = symbol;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUser_money() {
            return user_money;
        }

        public void setUser_money(String user_money) {
            this.user_money = user_money;
        }

        public String getChange_time() {
            return change_time;
        }

        public void setChange_time(String change_time) {
            this.change_time = change_time;
        }

        public String getChange_type() {
            return change_type;
        }

        public void setChange_type(String change_type) {
            this.change_type = change_type;
        }

        public String getChange_desc() {
            return change_desc;
        }

        public void setChange_desc(String change_desc) {
            this.change_desc = change_desc;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

}
