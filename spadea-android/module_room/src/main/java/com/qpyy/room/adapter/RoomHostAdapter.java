package com.qpyy.room.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.makeramen.roundedimageview.RoundedImageView;
import com.qpyy.libcommon.utils.ImageUtils;
import com.qpyy.room.R;
import com.qpyy.room.bean.AnchorRankingItemBean;


/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui.room.adapter
 * 创建人 王欧
 * 创建时间 2020/4/2 5:11 PM
 * 描述 describe
 */
public class RoomHostAdapter extends BaseQuickAdapter<AnchorRankingItemBean, BaseViewHolder> {
    public RoomHostAdapter() {
        super(R.layout.room_rv_item_room_host);
    }

    @Override
    protected void convert(BaseViewHolder helper, AnchorRankingItemBean item) {
        helper.setText(R.id.tv_no, String.valueOf(item.getRank()));
        RoundedImageView rivAvatar = helper.getView(R.id.riv_avatar);
        ImageUtils.loadHeadCC(item.getHead_picture(), rivAvatar);
        helper.setText(R.id.tv_name, item.getNickname());
        helper.setText(R.id.tv_desc, String.format("魅力:%s", item.getNumber()));
        if (item.getIs_follow() == 1) {
            helper.setBackgroundRes(R.id.tv_follow, R.mipmap.room_ic_anchor_ranking_item_followed);
        } else {
            helper.setBackgroundRes(R.id.tv_follow, R.mipmap.room_ic_anchor_ranking_item_follow);
        }
        helper.addOnClickListener(R.id.tv_follow);
    }
}
