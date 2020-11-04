package com.spadea.xqipao.data;

import com.stx.xhb.xbanner.entity.SimpleBannerInfo;

import java.util.ArrayList;

public class BannerModel extends SimpleBannerInfo {


    /**
     * ad_id : 11
     * type : 2
     * title : 鱼糖语音·开服送好礼
     * item_id : 9
     * link_url : null
     * content : https://yutangyuyin.oss-cn-hangzhou.aliyuncs.com/user-dir/YZrsGTJR5F.jpg
     * detail_pictures : null
     */

    private String ad_id;
    private String type;
    private String title;
    private String item_id;
    private String link_url;
    private String content;
    private ArrayList<String> detail_pictures;

    public String getAd_id() {
        return ad_id;
    }

    public void setAd_id(String ad_id) {
        this.ad_id = ad_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getLink_url() {
        return link_url;
    }

    public void setLink_url(String link_url) {
        this.link_url = link_url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ArrayList<String> getDetail_pictures() {
        return detail_pictures;
    }

    public void setDetail_pictures(ArrayList<String> detail_pictures) {
        this.detail_pictures = detail_pictures;
    }

    @Override
    public Object getXBannerUrl() {
        return content;
    }
}
