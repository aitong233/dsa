package com.qpyy.module.me.adapter;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.makeramen.roundedimageview.RoundedImageView;
import com.qpyy.libcommon.bean.UserBean;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.qpyy.libcommon.utils.ImageUtils;
import com.qpyy.module.me.R;
import com.qpyy.module.me.bean.FriendBean;

public class FriendsAdapter extends BaseQuickAdapter<FriendBean, BaseViewHolder> {

    public FriendsAdapter() {
        super(R.layout.me_item_friends);
    }

    @Override
    protected void convert(BaseViewHolder helper, FriendBean item) {
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(ARouteConstants.HOME_CHART)
                        .withString("userId", item.getEmchat_username())
                        .withString("nickname", item.getNickname())
                        .withString("avatar", item.getHead_picture())
                        .navigation();
            }
        });
        helper.getView(R.id.riv_user_head).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userId = item.getFriend_id();
                if (!TextUtils.isEmpty(item.getUser_id())) {
                    userId = item.getUser_id();
                } else if (!TextUtils.isEmpty(item.getFollowed_user())) {
                    userId = item.getFollowed_user();
                }
                ARouter.getInstance().build(ARouteConstants.USER_ZONE).withString("userId", userId).navigation();
            }
        });
        helper.setText(R.id.tv_nick_name, item.getNickname());
        ImageUtils.loadHeadCC(item.getHead_picture(), helper.getView(R.id.riv_user_head));
        setHeadBorder(helper.getView(R.id.riv_user_head), item.getSex());
        ImageUtils.loadImageView(item.getNobility_icon(), helper.getView(R.id.iv_nobility));
        ImageUtils.loadImageView(item.getLevel_icon(), helper.getView(R.id.iv_level));
        helper.setGone(R.id.iv_level, !TextUtils.isEmpty(item.getLevel_icon()));
        helper.setGone(R.id.iv_nobility, !TextUtils.isEmpty(item.getNobility_icon()));
    }

    private void setHeadBorder(RoundedImageView imageView, String sex) {
        imageView.setBorderColor(UserBean.MALE.equals(sex) ? Color.parseColor("#FF6765FF") : Color.parseColor("#FFFF8890"));
    }
}
