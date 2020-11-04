package com.qpyy.room.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.makeramen.roundedimageview.RoundedImageView;
import com.qpyy.libcommon.utils.ImageUtils;
import com.qpyy.room.R;
import com.qpyy.room.bean.SearchUserModel;

import java.util.List;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.adapter
 * 创建人 黄强
 * 创建时间 2020/7/31 10:06
 * 描述 describe
 */
public class RoomAddAdapter extends BaseQuickAdapter<SearchUserModel, BaseViewHolder> {
    private int addType = 0;

    public RoomAddAdapter(int layoutResId) {
        super(R.layout.room_rv_item_search_user);
        addType = layoutResId;
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchUserModel item) {
        if (addType == 0) {
            helper.getView(R.id.add_user_btn).setBackgroundResource(R.mipmap.room_admin_add_btn);//管理员
        } else {
            helper.getView(R.id.add_user_btn).setBackgroundResource(R.mipmap.room_add_blacklist_btn);//黑名单
        }
        //判断是否是黑名单或者是管理员，如果有就隐藏按钮
        if ("1".equals(item.getValue())) {
            helper.getView(R.id.add_user_btn).setVisibility(View.GONE);
        }else {
            helper.getView(R.id.add_user_btn).setVisibility(View.VISIBLE);
        }
        helper.addOnClickListener(R.id.add_user_btn);
        helper.setText(R.id.tv_room_user_name, item.getNickname())//用户名
                .setText(R.id.tv_room_user_id, item.getUser_code());//用户ID
        ImageUtils.loadHeadCC(item.getHead_picture(), helper.getView(R.id.riv_admin_head_icon));//头像
    }

}
