package com.qpyy.room.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qpyy.room.R;
import com.qpyy.room.bean.GiftNumBean;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.adapter
 * 创建人 黄强
 * 创建时间 2020/8/6 14:11
 * 描述 describe
 */
public class SelectGiftNumAdapter extends BaseQuickAdapter<GiftNumBean, BaseViewHolder> {

    public SelectGiftNumAdapter() {
        super(R.layout.room_rv_item_gift_num_windows);
    }

    @Override
    protected void convert(BaseViewHolder helper, GiftNumBean item) {
        helper.setText(R.id.tv_gift_mum, item.getNumber())
                .setText(R.id.tv_gift_name, item.getText());
        if ("0".equals(item.getNumber())) {
            helper.setText(R.id.tv_custom, item.getText());
            helper.setVisible(R.id.tv_custom,true);
            helper.setVisible(R.id.tv_gift_mum, false).setVisible(R.id.tv_gift_name, false);
        }else {
            helper.setVisible(R.id.tv_custom,false);
            helper.setVisible(R.id.tv_gift_mum, true).setVisible(R.id.tv_gift_name, true);
        }
        if("1".equals(item.getNumber())){
            helper.getView(R.id.iv_split).setVisibility(View.GONE);
        }

    }
}

