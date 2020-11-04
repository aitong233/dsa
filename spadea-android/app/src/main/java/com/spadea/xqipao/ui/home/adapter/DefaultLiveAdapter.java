package com.spadea.xqipao.ui.home.adapter;

import android.text.TextUtils;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.ImageLoader;
import com.spadea.xqipao.data.MultiRoomModel;
import com.spadea.xqipao.data.RoomModel;
import com.spadea.xqipao.utils.LabelUtil;

public class DefaultLiveAdapter extends BaseMultiItemQuickAdapter<MultiRoomModel, BaseViewHolder> {
    public DefaultLiveAdapter() {
        super(null);
        addItemType(0, R.layout.item_default_live);
        addItemType(1, R.layout.rv_item_default_live_top3);
    }


    @Override
    protected void convert(BaseViewHolder helper, MultiRoomModel model) {
        RoomModel item = model.mRoomModel;
        if (model.getItemType() == 0) {
            helper.setBackgroundRes(R.id.rl, item.getWeek_star_recommend() == 1 ? R.mipmap.bg_week_star_default : R.drawable.bg_r5_white);
            helper.setVisible(R.id.iv_week_star, item.getWeek_star_recommend() == 1);
            ImageLoader.loadImage(mContext, helper.getView(R.id.riv_head_img), item.getOwner_picture());
            if (TextUtils.isEmpty(item.getHolder_nickname())) {
                helper.setText(R.id.tv_host_name, "暂无主持");
            } else {
                helper.setText(R.id.tv_host_name, "主持人：" + item.getHolder_nickname());
            }
            helper.setText(R.id.tv_host_num, item.getPopularity()).setText(R.id.tv_room_name, item.getRoom_name());
            LabelUtil.setLabel(item.getLabel_name(), helper.getView(R.id.tv_label));

            if (item.getHave_password() == 0) {
                helper.setVisible(R.id.iv_room_lock, false);
            } else {
                helper.setVisible(R.id.iv_room_lock, true);
            }
        } else {
            helper.setVisible(R.id.iv_week_star, item.getWeek_star_recommend() == 1);
            helper.setText(R.id.tv_host_num, item.getPopularity());
            helper.setText(R.id.tv_room_name, item.getRoom_name());
            ImageLoader.loadImage(mContext, helper.getView(R.id.riv), item.getOwner_picture());
            LabelUtil.setLabel(item.getLabel_name(), helper.getView(R.id.tv_label));
            helper.setVisible(R.id.iv_room_lock, item.getHave_password() == 1);
        }
    }
}
