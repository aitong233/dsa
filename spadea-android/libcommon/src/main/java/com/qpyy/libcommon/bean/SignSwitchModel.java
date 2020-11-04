package com.qpyy.libcommon.bean;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.data
 * 创建人 王欧
 * 创建时间 2020/4/27 8:57 PM
 * 描述 describe
 */
public class SignSwitchModel {
    private int sign;
    private int labor;
    private int chat_min_level;
    private Children children;

    public int getChat_min_level() {
        return chat_min_level;
    }

    public void setChat_min_level(int chat_min_level) {
        this.chat_min_level = chat_min_level;
    }

    public Children getChildren() {
        return children;
    }

    public void setChildren(Children children) {
        this.children = children;
    }

    public int getSign() {
        return sign;
    }

    public void setSign(int sign) {
        this.sign = sign;
    }

    public int getLabor() {
        return labor;
    }

    public void setLabor(int labor) {
        this.labor = labor;
    }

    public static class Children{
        private int state;
        private String url;
        private String icon;

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }
    }
}
