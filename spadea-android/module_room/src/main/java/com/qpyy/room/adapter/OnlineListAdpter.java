package com.qpyy.room.adapter;

import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qpyy.libcommon.utils.ImageUtils;
import com.qpyy.libcommon.widget.RoleView;
import com.qpyy.room.R;
import com.qpyy.room.bean.OnlineListResp;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.adapter
 * 创建人 黄强
 * 创建时间 2020/7/25 11:01
 * 描述 describe
 */
public class OnlineListAdpter extends BaseQuickAdapter<OnlineListResp, BaseViewHolder> {
    private boolean manager;

    public OnlineListAdpter() {
        super(R.layout.room_rv_item_online);
    }

    @Override
    protected void convert(BaseViewHolder helper, OnlineListResp item) {
        //数据绑定赋值
        helper.setText(R.id.room_item_name, item.getNickname());
        helper.setText(R.id.room_item_id, "ID:" + item.getUser_code());
        helper.addOnClickListener(R.id.iv_bao_wheat);
        ImageUtils.loadHeadCC(item.getHead_picture(), helper.getView(R.id.room_item_head));
        String nobilityIcon = item.getNobility_icon();//爵位
        String rankIcon = item.getRank_icon();//等级
        ImageUtils.loadImageView(item.getNobility_icon(), helper.getView(R.id.room_item_nobility));
        ImageUtils.loadImageView(item.getRank_icon(), helper.getView(R.id.room_item_rank));
        RoleView roleView = helper.getView(R.id.iv_role);
        roleView.setRole(item.getRole());
        if (TextUtils.isEmpty(nobilityIcon)) {
            helper.getView(R.id.room_item_nobility).setVisibility(View.GONE);
        } else {
            helper.getView(R.id.room_item_nobility).setVisibility(View.VISIBLE);
        }
        if (TextUtils.isEmpty(rankIcon)) {
            helper.getView(R.id.room_item_rank).setVisibility(View.GONE);
        } else {
            helper.getView(R.id.room_item_rank).setVisibility(View.VISIBLE);
        }
        if (item.getUser_is_new() == 1) {
            helper.getView(R.id.room_item_news).setVisibility(View.VISIBLE);
        } else {
            helper.getView(R.id.room_item_news).setVisibility(View.GONE);
        }
        helper.setGone(R.id.room_item_bg, helper.getAdapterPosition() % 2 == 0);
        helper.setGone(R.id.iv_pretty, "1".equals(item.getGood_number()));
        helper.addOnClickListener(R.id.room_item_head);
        //普通用户隐藏抱麦

        helper.getView(R.id.iv_bao_wheat).setVisibility(manager && "0".equals(item.getPit_number()) ? View.VISIBLE : View.GONE);
    }

    //是否爆麦
    public void setManager(boolean manager) {
        this.manager = manager;
        notifyDataSetChanged();
    }
}
