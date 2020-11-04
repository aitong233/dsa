package com.qpyy.room.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qpyy.room.R;
import com.qpyy.room.bean.OnlineListResp;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.adapter
 * 创建人 黄强
 * 创建时间 2020/8/6 10:39
 * 描述 describe
 */
public class GiftWheatListAdapter extends BaseQuickAdapter<OnlineListResp, BaseViewHolder> {

    public GiftWheatListAdapter(int layoutResId) {
        super(R.layout.room_rv_item_gift_wheat);
    }

    @Override
    protected void convert(BaseViewHolder helper, OnlineListResp item) {

    }
}
