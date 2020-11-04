package com.qpyy.module.me.bean;

import java.util.List;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.module.me.bean
 * 创建人 王欧
 * 创建时间 2020/7/10 1:43 PM
 * 描述 describe
 */
public class SearchFriendResp {
    private String count;
    private List<FriendBean> list;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<FriendBean> getList() {
        return list;
    }

    public void setList(List<FriendBean> list) {
        this.list = list;
    }
}
