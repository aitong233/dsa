package com.spadea.xqipao.data;

public class NewsModel {

    /**
     * count : 1
     * info : {"id":"1","user_id":"546034","content":"您关注的主播花无缺上线开播了，抓紧加入吧","add_time":"2019-10-11 08:25","status":"0"}
     */

    private int count;
    private InfoBean info;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public static class InfoBean {
        /**
         * id : 1
         * user_id : 546034
         * content : 您关注的主播花无缺上线开播了，抓紧加入吧
         * add_time : 2019-10-11 08:25
         * status : 0
         */

        private String id;
        private String user_id;
        private String content;
        private String add_time;
        private String status;

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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
