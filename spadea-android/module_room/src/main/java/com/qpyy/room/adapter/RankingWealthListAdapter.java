package com.qpyy.room.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ConvertUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qpyy.libcommon.bean.UserBean;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.qpyy.libcommon.utils.ImageLoader;
import com.qpyy.libcommon.utils.ImageUtils;
import com.qpyy.room.R;
import com.qpyy.room.bean.WealthRankingResp;

import java.util.List;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.adapter
 * 创建人 黄强
 * 创建时间 2020/7/25 10:37
 * 描述 describe
 */
public class RankingWealthListAdapter extends BaseQuickAdapter<WealthRankingResp.ListsBean, BaseViewHolder> {

    public RankingWealthListAdapter(){
        super(R.layout.room_rv_item_ranking);
    }

    @Override
    protected void convert(BaseViewHolder helper, WealthRankingResp.ListsBean item) {
        //数据绑定赋值
        helper.setText(R.id.room_item_seq, String.valueOf(helper.getAdapterPosition() + 4));//从第四个开始设置值
        helper.setText(R.id.room_item_name, item.getNickname());
        helper.setText(R.id.room_item_id, "ID:" + item.getUser_code());
        helper.setText(R.id.room_item_pop, item.getNumber_format());
        ImageUtils.loadHeadCC(item.getHead_picture(), helper.getView(R.id.room_item_head));
        String nobility = item.getLevel_icon();//等级
        String status = item.getNobility_icon();//爵位
        ImageUtils.loadImageView(item.getNobility_icon(), helper.getView(R.id.room_item_rank));
        ImageUtils.loadImageView(item.getLevel_icon(), helper.getView(R.id.room_item_grade));
        if (TextUtils.isEmpty(nobility)) {
            helper.getView(R.id.room_item_grade).setVisibility(View.GONE);
        } else {
            helper.getView(R.id.room_item_grade).setVisibility(View.VISIBLE);
        }
        if (TextUtils.isEmpty(status)) {
            helper.getView(R.id.room_item_rank).setVisibility(View.GONE);
        } else {
            helper.getView(R.id.room_item_rank).setVisibility(View.VISIBLE);
        }
        if (helper.getAdapterPosition() % 2 == 1) {
            helper.getView(R.id.room_item_bg).setVisibility(View.GONE);//隔开隐藏背景
        }
        helper.addOnClickListener(R.id.room_item_head);
    }

}
