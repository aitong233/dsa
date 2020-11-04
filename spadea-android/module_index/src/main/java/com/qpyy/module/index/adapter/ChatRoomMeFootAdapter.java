package com.qpyy.module.index.adapter;

import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qpyy.libcommon.utils.ImageUtils;
import com.qpyy.module.index.R;
import com.qpyy.module.index.bean.MyFootResp;

/**
 * 历史记录
 */
public class ChatRoomMeFootAdapter extends BaseQuickAdapter<MyFootResp, BaseViewHolder> {

    public ChatRoomMeFootAdapter() {
        super(R.layout.index_item_chatroom_foot);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyFootResp item) {
        ImageUtils.loadHeadCC(item.getRoomPicture(), helper.getView(R.id.riv_user_head));
        if (TextUtils.isEmpty(item.getLabel_icon())) {
            helper.setGone(R.id.iv_room_labe, false);
        } else {
            helper.setGone(R.id.iv_room_labe, true);
            ImageUtils.loadImageView(item.getLabel_icon(), helper.getView(R.id.iv_room_labe));
        }
        ImageUtils.loadGift(helper.getView(R.id.iv_hot),ImageUtils.ANIM);
        helper.setText(R.id.tv_room_name, item.getRoom_name());
        helper.setText(R.id.tv_room_id, "ID: " + item.getRoom_code());
        helper.setText(R.id.tv_popularity, item.getPopularity());
        helper.setVisible(R.id.rl_lock, item.getLocked() == 1);
    }
}
