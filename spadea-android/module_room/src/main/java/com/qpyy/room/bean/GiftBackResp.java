package com.qpyy.room.bean;

import com.qpyy.libcommon.bean.GiftModel;

import java.util.List;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.bean
 * 创建人 王欧
 * 创建时间 2020/8/15 4:23 PM
 * 描述 describe
 */
public class GiftBackResp {
    private List<GiftModel> list;
    private String total_price;



    public List<GiftModel> getList() {
        return list;
    }

    public void setList(List<GiftModel> list) {
        this.list = list;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }
}
