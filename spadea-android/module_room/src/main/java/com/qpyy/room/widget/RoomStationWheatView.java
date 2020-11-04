package com.qpyy.room.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import com.blankj.utilcode.util.ObjectUtils;
import com.qpyy.libcommon.bean.UserBean;
import com.qpyy.libcommon.utils.ImageUtils;
import com.qpyy.room.R;
import com.qpyy.room.R2;
import com.qpyy.room.bean.RoomPitBean;

import butterknife.BindView;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.utils.view
 * 创建人 王欧
 * 创建时间 2020/4/9 12:46 PM
 * 描述 describe
 */
public class RoomStationWheatView extends BaseWheatView {

    @BindView(R2.id.riv_wheat)
    ImageView mRivWheat;

    public RoomStationWheatView(Context context) {
        this(context, null, 0);
    }

    public RoomStationWheatView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoomStationWheatView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setUpLayoutParams();
    }

    @Override
    protected void initPit(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoomStationWheatView);
        pitNumber = typedArray.getString(R.styleable.RoomStationWheatView_room_station_wheat_number);
        int picRes = typedArray.getResourceId(R.styleable.RoomStationWheatView_room_station_pic, -1);
        if (picRes != -1) {
            mIvFrame.setImageResource(picRes);
        }
        typedArray.recycle();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.room_view_station_wheat;
    }

    @Override
    protected void setPitData(RoomPitBean bean) {
        mIvSex.setVisibility(INVISIBLE);
        //麦位上锁
        ImageUtils.loadRes(isLocked() ? R.mipmap.room_wheat_islock : R.mipmap.room_ic_wheat_default, mRivWheat);
        if (ObjectUtils.isEmpty(bean.getUser_id()) || "0".equals(bean.getUser_id())) {
            if (isHost()) {
                mIvFrame.setVisibility(INVISIBLE);
            }
            mRiv.setVisibility(INVISIBLE);
            mTvName.setText("");
            mIvFace.remove();
            //停止声浪
            mIvRipple.setVisibility(INVISIBLE);
        } else {
            //开启声浪
            mIvRipple.setVisibility(VISIBLE);
            mRiv.setVisibility(VISIBLE);
            mTvName.setText(bean.getNickname());
            ImageUtils.loadHeadCC(bean.getHead_picture(), mRiv);
            if (isHost()) {
                if (TextUtils.isEmpty(pitBean.getDress_picture())) {
                    mIvFrame.setVisibility(INVISIBLE);
                    mIvSex.setVisibility(VISIBLE);
                    if (!TextUtils.isEmpty(bean.getSex())) {
                        if (UserBean.MALE.equals(bean.getSex())) {
                            mIvSex.setBackgroundResource(R.mipmap.room_wheat_ic_headportriat_boy);
                        } else {
                            mIvSex.setBackgroundResource(R.mipmap.room_wheat_ic_headportriat_girl);
                        }
                    } else {
                        mIvSex.setVisibility(GONE);
                        Log.e("麦位数据：", "性别获取为空");
                    }
                } else {
                    mIvFrame.setVisibility(VISIBLE);
                    ImageUtils.loadDecorationAvatar(pitBean.getDress_picture(), mIvFrame);
                }
            }
        }
    }

    /**
     * 更新主持麦约束
     */
    private void setUpLayoutParams() {
        if (isHost()) {
            ConstraintLayout.LayoutParams layoutParams = (LayoutParams) mRiv.getLayoutParams();
            layoutParams.matchConstraintPercentWidth = 0.70154f;
            mRiv.setLayoutParams(layoutParams);
            ConstraintLayout.LayoutParams wheatLayoutParamsParams = (LayoutParams) mRivWheat.getLayoutParams();
            wheatLayoutParamsParams.matchConstraintPercentWidth = 0.70154f;
            mRivWheat.setLayoutParams(wheatLayoutParamsParams);
        }
    }
}
