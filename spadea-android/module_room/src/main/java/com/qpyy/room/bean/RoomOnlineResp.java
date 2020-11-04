package com.qpyy.room.bean;

import java.util.List;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.libcommon.bean
 * 创建人 王欧
 * 创建时间 2020/8/14 3:35 PM
 * 描述 describe
 */
public class RoomOnlineResp {
    private List<OnlineListResp> list;
    private String total;

    public List<OnlineListResp> getList() {
        return list;
    }

    public void setList(List<OnlineListResp> list) {
        this.list = list;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
