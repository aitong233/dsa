package com.qpyy.room.bean;

public class NewsListBean {

    /**
     * id : 1
     * user_id : 546034
     * content : 您关注的主播花无缺上线开播了，抓紧加入吧
     * add_time : 2019-10-11 08:25
     * status : 1
     */

    private String id;
    private String user_id;
    private String content;
    private String add_time;
    private int status;
    private String messages_id;
    private String title;
    private String news_type;
    private String action_type;
    private String action_id;
    private String user_id_from;

    public String getMessages_id() {
        return messages_id;
    }

    public void setMessages_id(String messages_id) {
        this.messages_id = messages_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNews_type() {
        return news_type;
    }

    public void setNews_type(String news_type) {
        this.news_type = news_type;
    }

    public String getAction_type() {
        return action_type;
    }

    public void setAction_type(String action_type) {
        this.action_type = action_type;
    }

    public String getAction_id() {
        return action_id;
    }

    public void setAction_id(String action_id) {
        this.action_id = action_id;
    }

    public String getUser_id_from() {
        return user_id_from;
    }

    public void setUser_id_from(String user_id_from) {
        this.user_id_from = user_id_from;
    }

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
