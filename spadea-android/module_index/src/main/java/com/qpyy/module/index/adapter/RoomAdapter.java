package com.qpyy.module.index.adapter;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;

import com.blankj.utilcode.util.ConvertUtils;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qpyy.libcommon.utils.ImageUtils;
import com.qpyy.module.index.R;
import com.qpyy.module.index.bean.MultiRoomModel;
import com.qpyy.module.index.bean.RoomModel;


public class RoomAdapter extends BaseMultiItemQuickAdapter<MultiRoomModel, BaseViewHolder> {
    public static final int TYPE_TOP = 1;
    public static final int TYPE_NORMAL = 0;

    public RoomAdapter() {
        super(null);
        addItemType(TYPE_NORMAL, R.layout.index_rv_item_room_normal);
        addItemType(TYPE_TOP, R.layout.index_rv_item_room_top);
    }


    @Override
    protected void convert(BaseViewHolder helper, MultiRoomModel model) {
        RoomModel item = model.mRoomModel;
        if (model.getItemType() == TYPE_TOP) {
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
            helper.setVisible(R.id.rl_lock, item.getLocked() == 1);
        } else {
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
            ImageUtils.loadImageView(item.getSpecial_flag_small(), helper.getView(R.id.iv_week_star));
            helper.setVisible(R.id.rl_lock, item.getLocked() == 1);
        }
    }
}
