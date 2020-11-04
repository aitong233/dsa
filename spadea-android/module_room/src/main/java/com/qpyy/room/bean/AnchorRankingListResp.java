package com.qpyy.room.bean;

import java.util.List;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.data
 * 创建人 王欧
 * 创建时间 2020/4/9 7:29 PM
 * 描述 describe
 */
public class AnchorRankingListResp {
    private List<AnchorRankingItemBean> list;
    private List<AnchorRankingItemBean> my_ranking;

    public List<AnchorRankingItemBean> getList() {
        return list;
    }

    public void setList(List<AnchorRankingItemBean> list) {
        this.list = list;
    }

    public List<AnchorRankingItemBean> getMy_ranking() {
        return my_ranking;
    }

    public void setMy_ranking(List<AnchorRankingItemBean> my_ranking) {
        this.my_ranking = my_ranking;
    }
}
