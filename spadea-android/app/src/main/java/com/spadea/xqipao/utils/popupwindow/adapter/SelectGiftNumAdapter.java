package com.spadea.xqipao.utils.popupwindow.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.spadea.xqipao.data.GiftNumBean;
import com.spadea.yuyin.R;

public class SelectGiftNumAdapter extends BaseQuickAdapter<GiftNumBean, BaseViewHolder> {

    public SelectGiftNumAdapter() {
        super(R.layout.item_gift_right);
    }

    @Override
    protected void convert(BaseViewHolder helper, GiftNumBean item) {
        helper.setText(R.id.tv0, item.getNum())
                .setText(R.id.tv1, item.getName());
    }
}
