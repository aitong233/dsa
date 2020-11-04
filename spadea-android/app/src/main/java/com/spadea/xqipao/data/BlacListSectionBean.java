package com.spadea.xqipao.data;

import com.spadea.yuyin.ui.fragment2.setting.blacklist.BlackListBean;

import java.util.List;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.data
 * 创建人 王欧
 * 创建时间 2020/4/3 12:22 PM
 * 描述 describe
 */
public class BlacListSectionBean {
    private String index;

    private List<BlackListBean> items;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public List<BlackListBean> getItems() {
        return items;
    }

    public void setItems(List<BlackListBean> items) {
        this.items = items;
    }
}
