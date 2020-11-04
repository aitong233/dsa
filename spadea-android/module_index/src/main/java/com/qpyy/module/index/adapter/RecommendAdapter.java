package com.qpyy.module.index.adapter;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;

import com.blankj.utilcode.util.ConvertUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qpyy.libcommon.utils.ImageUtils;
import com.qpyy.module.index.R;
import com.qpyy.module.index.bean.RoomModel;


public class RecommendAdapter extends BaseQuickAdapter<RoomModel, BaseViewHolder> {

    public RecommendAdapter() {
        super(R.layout.index_rv_item_room_recommend, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, RoomModel item) {
        ConstraintLayout cl = helper.getView(R.id.cl);
        if (TextUtils.isEmpty(item.getSpecial_flag_small())) {
            cl.setBackgroundResource(R.drawable.index_bg_normal_item);
        } else if (!TextUtils.isEmpty(item.getSpecial_flag_small_color())) {
            GradientDrawable drawable = new GradientDrawable();
            drawable.setColor(Color.WHITE);
            drawable.setStroke(ConvertUtils.dp2px(1), Color.parseColor(item.getSpecial_flag_small_color()));
            drawable.setCornerRadius(ConvertUtils.dp2px(10));
            cl.setBackground(drawable);
        }

        helper.setText(R.id.tv_room_id, String.format("ID:%s", item.getRoom_code()));
        ImageUtils.loadGift(helper.getView(R.id.iv_online), ImageUtils.ANIM);
        ImageUtils.loadHeadCC(item.getRoomPicture(), helper.getView(R.id.riv));
        helper.setText(R.id.tv_name, item.getRoom_name());
        helper.setText(R.id.tv_online, item.getPopularity());
        ImageUtils.loadImageView(item.getLabel_icon(), helper.getView(R.id.iv_tag));
        helper.setVisible(R.id.rl_lock, item.getLocked() == 1);
        ImageUtils.loadImageView(item.getSpecial_flag_small(), helper.getView(R.id.iv_week_star));
    }
}
