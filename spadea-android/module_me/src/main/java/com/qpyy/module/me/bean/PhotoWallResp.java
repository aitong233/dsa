package com.qpyy.module.me.bean;

import java.io.Serializable;
import java.util.List;

public class PhotoWallResp implements Serializable {

    private String vedio;
    private String avatar;
    private String vedio_cover;

    private List<GiftResp> list;

    public String getVedio() {
        return vedio;
    }

    public void setVedio(String vedio) {
        this.vedio = vedio;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getVedio_cover() {
        return vedio_cover;
    }

    public void setVedio_cover(String vedio_cover) {
        this.vedio_cover = vedio_cover;
    }

    public List<GiftResp> getList() {
        return list;
    }

    public void setList(List<GiftResp> list) {
        this.list = list;
    }

    public static class GiftResp implements Serializable {

        private String id;
        private String url;



        public GiftResp() {

        }

        public GiftResp(String id, String url) {
            this.id = id;
            this.url = url;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }


}
