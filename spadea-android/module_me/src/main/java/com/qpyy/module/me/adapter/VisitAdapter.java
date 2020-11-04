package com.qpyy.module.me.adapter;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.makeramen.roundedimageview.RoundedImageView;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.qpyy.libcommon.utils.ImageLoader;
import com.qpyy.module.me.R;
import com.qpyy.module.me.bean.ComeUserResp;

public class VisitAdapter extends BaseQuickAdapter<ComeUserResp, BaseViewHolder> {


    public VisitAdapter() {
        super(R.layout.me_item_visit);
    }

    @Override
    protected void convert(BaseViewHolder helper, ComeUserResp item) {
        ImageLoader.loadHead(mContext, helper.getView(R.id.riv_user_head), item.getHead_picture());
        helper.setVisible(R.id.iv_nobility, !TextUtils.isEmpty(item.getNobility_icon()));
        ImageLoader.loadIcon(mContext, helper.getView(R.id.iv_nobility), item.getNobility_icon());
        ImageLoader.loadIcon(mContext, helper.getView(R.id.iv_grade), item.getLevel_icon());
        helper.setGone(R.id.iv_grade, !TextUtils.isEmpty(item.getLevel_icon()));
        helper.setText(R.id.tv_nick_name, item.getNickname()).setText(R.id.tv_time, item.getAdd_time());
        RoundedImageView roundedImageView = helper.getView(R.id.riv_user_head);
        if ("1".equals(item.getSex())) {
            roundedImageView.setBorderColor(Color.parseColor("#6765FF"));
        } else {
            roundedImageView.setBorderColor(Color.parseColor("#FA447D"));
        }
        helper.getView(R.id.riv_user_head).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(ARouteConstants.USER_DETAILS).withString("userId", item.getVisit_user()).navigation();
            }
        });

    }
}
