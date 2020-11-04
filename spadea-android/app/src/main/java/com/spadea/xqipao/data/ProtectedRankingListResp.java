package com.spadea.xqipao.data;

import java.util.List;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.common
 * 创建人 王欧
 * 创建时间 2020/4/9 7:28 PM
 * 描述 describe
 */
public class ProtectedRankingListResp {
    private List<ProtectedRankingItemBean> list;
    private String total;
    private ProtectedRankingItemBean my_info;

    public ProtectedRankingItemBean getMy_info() {
        return my_info;
    }

    public void setMy_info(ProtectedRankingItemBean my_info) {
        this.my_info = my_info;
    }

    public List<ProtectedRankingItemBean> getList() {
        return list;
    }

    public void setList(List<ProtectedRankingItemBean> list) {
        this.list = list;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
