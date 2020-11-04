package com.qpyy.module.me.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qpyy.libcommon.utils.ImageLoader;
import com.qpyy.module.me.R;
import com.qpyy.module.me.bean.GiftBean;

public class UserGiftWallAdapter extends BaseQuickAdapter<GiftBean, BaseViewHolder> {


    public UserGiftWallAdapter() {
        super(R.layout.me_item_user_gift_wall);
    }

    @Override
    protected void convert(BaseViewHolder helper, GiftBean item) {
        ImageLoader.loadImage(mContext, helper.getView(R.id.riv_user_head), item.getPicture());
        helper.setText(R.id.tv_gift_name, item.getName()).setText(R.id.tv_number, item.getNumber());
    }


}
