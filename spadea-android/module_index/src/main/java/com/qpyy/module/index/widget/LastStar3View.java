package com.qpyy.module.index.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.qpyy.libcommon.bean.UserBean;
import com.qpyy.libcommon.utils.ImageUtils;
import com.qpyy.module.index.R;
import com.qpyy.module.index.R2;
import com.qpyy.module.index.bean.LastWeekStarResp;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.module.index.widget
 * 创建人 王欧
 * 创建时间 2020/7/4 5:20 PM
 * 描述 describe
 */
public class LastStar3View extends LinearLayout {
    @BindView(R2.id.iv_gift)
    ImageView mIvGift;
    @BindView(R2.id.tv_gift)
    TextView mTvGift;
    @BindView(R2.id.riv)
    RoundedImageView mRiv;
    @BindView(R2.id.tv_name)
    TextView mTvName;
    @BindView(R2.id.iv_vip)
    ImageView mIvVip;
    @BindView(R2.id.iv_level)
    ImageView mIvLevel;
    @BindView(R2.id.ll_vip)
    LinearLayout mLlVip;
    @BindView(R2.id.tv_charm)
    TextView mTvCharm;
    @BindView(R2.id.tv_follow)
    TextView mTvFollow;
    @BindView(R2.id.iv_follow)
    ImageView mIvFollow;
    @BindView(R2.id.ll_follow)
    LinearLayout mLlFollow;

    public LastStar3View(Context context) {
        this(context, null);
    }

    public LastStar3View(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.index_rv_item_last_week_star_top3, this);
        ButterKnife.bind(this);
    }

    public void setData(LastWeekStarResp.TopRichThreeBean item) {
        mTvName.setText(item.getNickname());
        mTvGift.setText(item.getGift_name());
        mTvCharm.setText(item.getTotal_price());
        mIvVip.setVisibility(TextUtils.isEmpty(item.getNobility_icon()) ? GONE : VISIBLE);
        ImageUtils.loadHeadCC(item.getHead_picture(), mRiv);
        ImageUtils.loadImageView(item.getGift_picture(), mIvGift);
        ImageUtils.loadImageView(item.getLevel_icon(), mIvLevel);
        ImageUtils.loadImageView(item.getNobility_icon(), mIvVip);
        mRiv.setBorderColor(UserBean.MALE.equals(item.getSex()) ? Color.parseColor("#FF6765FF") : Color.parseColor("#FFFF8890"));
    }
}
