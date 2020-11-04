package com.qpyy.module.index.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qpyy.libcommon.utils.ImageUtils;
import com.qpyy.module.index.R;
import com.qpyy.module.index.bean.RecommendAttentionResp;

/**
 * 历史记录
 */
public class ChatRoomMeRecommendFollowAdapter extends BaseQuickAdapter<RecommendAttentionResp, BaseViewHolder> {

    public ChatRoomMeRecommendFollowAdapter() {
        super(R.layout.index_item_chatroom_follow);
    }

    @Override
    protected void convert(BaseViewHolder helper, RecommendAttentionResp item) {
        ImageUtils.loadGift(helper.getView(R.id.iv_hot), ImageUtils.ANIM);
        ImageUtils.loadHeadCC(item.getRoomPicture(), helper.getView(R.id.riv_room_picture));
        helper.setText(R.id.tv_room_name, item.getRoom_name());
        helper.setText(R.id.tv_popularity, item.getPopularity());

    }
}
