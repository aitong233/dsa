package com.qpyy.module.index.adapter;

import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qpyy.libcommon.utils.ImageUtils;
import com.qpyy.module.index.R;
import com.qpyy.module.index.bean.RoomResultResp;

/**
 * 搜索房间结果
 */
public class SearchRoomResultAdapter extends BaseQuickAdapter<RoomResultResp.RoomResultInfo, BaseViewHolder> {


    public SearchRoomResultAdapter() {
        super(R.layout.index_item_search_room_result);
    }

    @Override
    protected void convert(BaseViewHolder helper, RoomResultResp.RoomResultInfo item) {
        helper.setText(R.id.tv_anchor, item.getNickname());
        helper.setText(R.id.tv_popularity, item.getPopularity());
        helper.setText(R.id.tv_room_name, item.getRoom_name());
        if (TextUtils.isEmpty(item.getLabel_icon())) {
            helper.setGone(R.id.iv_room_labe, false);
        } else {
            helper.setGone(R.id.iv_room_labe, true);
            ImageUtils.loadImageView(item.getLabel_icon(), helper.getView(R.id.iv_room_labe));
        }
        ImageUtils.loadHeadCC(item.getCover_picture(), helper.getView(R.id.riv_room_cover));
        if (item.getLocked() == 1) {
            helper.setVisible(R.id.riv_room_lock, true);
        } else {
            helper.setVisible(R.id.riv_room_lock, false);
        }
    }

}
