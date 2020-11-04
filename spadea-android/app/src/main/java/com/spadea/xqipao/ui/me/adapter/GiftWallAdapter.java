package com.spadea.xqipao.ui.me.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.spadea.xqipao.data.UserInfoDataModel;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.ImageLoader;

public class GiftWallAdapter extends BaseQuickAdapter<UserInfoDataModel.GiftListBean, BaseViewHolder> {

    public GiftWallAdapter() {
        super(R.layout.item_gift_wall);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserInfoDataModel.GiftListBean item) {
        ImageLoader.loadImage(mContext, helper.getView(R.id.iv_img), item.getPicture());
        helper.setText(R.id.tv_name, item.getName())
                .setText(R.id.tv_num, "x"+item.getNumber());

    }
}
