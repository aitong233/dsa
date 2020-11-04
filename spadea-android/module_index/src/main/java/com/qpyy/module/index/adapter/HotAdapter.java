package com.qpyy.module.index.adapter;

import android.text.TextUtils;
import android.view.animation.Animation;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qpyy.libcommon.utils.ImageUtils;
import com.qpyy.module.index.R;
import com.qpyy.module.index.bean.RoomModel;


public class HotAdapter extends BaseQuickAdapter<RoomModel, BaseViewHolder> {

    public HotAdapter() {
        super(R.layout.index_rv_item_room_hot, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, RoomModel item) {
        ImageUtils.loadImageView(item.getSpecial_flag_big(), helper.getView(R.id.iv_week_star));
        ImageUtils.loadHeadCC(item.getRoomPicture(), helper.getView(R.id.riv));
        helper.setText(R.id.tv_name, item.getRoom_name());
        if (TextUtils.isEmpty(item.getHolder_nickname())) {
            helper.setText(R.id.tv_user_name, "暂无主持");
        } else {
            helper.setText(R.id.tv_user_name, item.getHolder_nickname());
        }
        helper.setText(R.id.tv_hot, item.getPopularity());
        ImageUtils.loadImageView(item.getLabel_icon(), helper.getView(R.id.iv_tag));
        ImageUtils.loadGift(helper.getView(R.id.iv_hot), ImageUtils.ANIM);
    }
}
