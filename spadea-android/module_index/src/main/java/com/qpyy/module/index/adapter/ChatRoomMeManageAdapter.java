package com.qpyy.module.index.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qpyy.libcommon.utils.ImageUtils;
import com.qpyy.module.index.R;
import com.qpyy.module.index.bean.ManageRoomResp;

import java.util.List;

/**
 * 管理房间
 */
public class ChatRoomMeManageAdapter extends BaseQuickAdapter<ManageRoomResp, BaseViewHolder> {

    private int index = -1;

    public ChatRoomMeManageAdapter() {
        super(R.layout.index_item_chatroom_me_manage);
    }

    @Override
    protected void convert(BaseViewHolder helper, ManageRoomResp item) {
        ImageUtils.loadGift(helper.getView(R.id.iv_hot), ImageUtils.ANIM);
        ImageUtils.loadHeadCC(item.getRoomPicture(), helper.getView(R.id.riv_user_head));
        ImageUtils.loadImageView(item.getLabel_icon(), helper.getView(R.id.iv_room_labe));
        helper.setText(R.id.tv_room_name, item.getRoom_name());
        helper.setText(R.id.tv_room_id, "ID: " + item.getRoom_code());
        helper.setText(R.id.tv_popularity, item.getPopularity());
        if (helper.getAdapterPosition() == index) {
            helper.setBackgroundColor(R.id.rl_root, Color.parseColor("#FAFAFA"));
            helper.setVisible(R.id.iv_clean, true);
        } else {
            helper.setBackgroundColor(R.id.rl_root, Color.parseColor("#FFFFFF"));
            helper.setVisible(R.id.iv_clean, false);
        }
        helper.addOnClickListener(R.id.iv_clean);
        helper.setVisible(R.id.rl_lock, item.getLocked() == 1);
    }

    public void setIndex(int index) {
        this.index = index;
        notifyDataSetChanged();
    }

    @Override
    public void setNewData(@Nullable List<ManageRoomResp> data) {
        index = -1;
        super.setNewData(data);
    }
}
