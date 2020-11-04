package com.qpyy.room.adapter;

import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.makeramen.roundedimageview.RoundedImageView;
import com.qpyy.libcommon.utils.ImageUtils;
import com.qpyy.room.R;
import com.qpyy.room.bean.ProtectedRankingItemBean;


/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui.room.adapter
 * 创建人 王欧
 * 创建时间 2020/4/2 3:12 PM
 * 描述 describe
 */
public class RoomGuardAdapter extends BaseQuickAdapter<ProtectedRankingItemBean, BaseViewHolder> {
    public RoomGuardAdapter() {
        super(R.layout.room_rv_item_room_guard);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProtectedRankingItemBean item) {
        if (helper.getAdapterPosition() == 1) {
            helper.setVisible(R.id.tv_no, false);
            helper.setVisible(R.id.iv_no, true);
            helper.setBackgroundRes(R.id.iv_no, R.mipmap.room_ic_guard_rank2);
            helper.itemView.setBackgroundResource(R.mipmap.room_bg_guard_list_item);
        } else if (helper.getAdapterPosition() == 2) {
            helper.setVisible(R.id.tv_no, false);
            helper.setVisible(R.id.iv_no, true);
            helper.setBackgroundRes(R.id.iv_no, R.mipmap.room_ic_guard_rank3);
            helper.itemView.setBackgroundResource(R.mipmap.room_bg_guard_list_item);
        } else {
            helper.setVisible(R.id.tv_no, true);
            helper.setVisible(R.id.iv_no, false);
            helper.itemView.setBackgroundColor(Color.TRANSPARENT);
        }
        helper.setText(R.id.tv_no, String.valueOf(helper.getAdapterPosition() + 1));
        RoundedImageView rivAvatar = helper.getView(R.id.riv_avatar);
        ImageUtils.loadHeadCC(item.getHead_picture(), rivAvatar);
        helper.setText(R.id.tv_name, item.getNickname());
        helper.setText(R.id.tv_desc, String.format("%s位：剩余%s天", item.getType_name(), item.getDays()));
        RecyclerView recyclerView = helper.getView(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(new GuardMedalAdapter(item.getProtect_info()));

    }
}
