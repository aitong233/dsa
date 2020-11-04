package com.qpyy.libcommon.widget;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.orhanobut.logger.Logger;
import com.qpyy.libcommon.R;
import com.qpyy.libcommon.R2;
import com.qpyy.libcommon.bean.UserBean;
import com.qpyy.libcommon.utils.ImageUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.utils.view
 * 创建人 王欧
 * 创建时间 2020/4/9 12:46 PM
 * 描述 describe
 */
public class DecorationHeadView extends ConstraintLayout {
    @BindView(R2.id.riv)
    RoundedImageView mRiv;

    @BindView(R2.id.iv_frame)
    ImageView mIvFrame;

    @BindView(R2.id.iv_sex)
    ImageView mIvSex;

    @BindView(R2.id.iv_online)
    ImageView mIvOnline;

    public DecorationHeadView(Context context) {
        this(context, null, 0);
    }

    public DecorationHeadView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DecorationHeadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs, defStyleAttr);
    }

    private void initView(Context context, AttributeSet attrs, int defStyleAttr) {
        LayoutInflater.from(context).inflate(R.layout.common_view_decoration_head, this, true);
        ButterKnife.bind(this);
    }

    public void setData(String headPicture, String framePicture, String sex) {
        Logger.e(headPicture, framePicture, sex);
        if (!TextUtils.isEmpty(headPicture)) {
            ImageUtils.loadHeadCC(headPicture, mRiv);
        }
        if (TextUtils.isEmpty(framePicture)) {
            mIvSex.setVisibility(VISIBLE);
            if (!TextUtils.isEmpty(sex)) {
                if (UserBean.MALE.equals(sex)) {
                    mIvSex.setBackgroundResource(R.mipmap.common_ic_headportriat_boy);
                } else {
                    mIvSex.setBackgroundResource(R.mipmap.common_ic_headportriat_girl);
                }
            } else {
                mIvSex.setBackgroundResource(R.drawable.common_bg_head_white);
            }
        } else {
            mIvSex.setVisibility(GONE);
        }
        ImageUtils.loadImageView(framePicture, mIvFrame);
    }

    public void setOnline(boolean isOnline) {
        mIvOnline.setVisibility(VISIBLE);
        mIvOnline.setImageResource(isOnline ? R.mipmap.me_online_icon : R.mipmap.me_icon_unchecked);
    }
}
