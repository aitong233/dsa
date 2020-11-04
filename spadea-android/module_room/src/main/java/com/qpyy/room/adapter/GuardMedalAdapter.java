package com.qpyy.room.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qpyy.room.R;
import com.qpyy.room.bean.ProtectedItemBean;


import java.util.List;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui.room.adapter
 * 创建人 王欧
 * 创建时间 2020/4/10 2:06 PM
 * 描述 describe
 */
public class GuardMedalAdapter extends BaseQuickAdapter<ProtectedItemBean, BaseViewHolder> {
    public GuardMedalAdapter(@Nullable List<ProtectedItemBean> data) {
        super(R.layout.room_rv_item_gurad_medal, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProtectedItemBean item) {
        if ("1".equals(item.getType())) {
            helper.setImageResource(R.id.iv_medal, R.mipmap.room_ic_guard_list_item_gold);
        } else if ("2".equals(item.getType())) {
            helper.setImageResource(R.id.iv_medal, R.mipmap.room_ic_guard_list_item_silver);
        } else if ("3".equals(item.getType())) {
            helper.setImageResource(R.id.iv_medal, R.mipmap.room_ic_guard_list_item_bronze);
        }
    }
}
