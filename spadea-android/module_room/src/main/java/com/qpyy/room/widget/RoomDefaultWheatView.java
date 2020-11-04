package com.qpyy.room.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.blankj.utilcode.util.ObjectUtils;
import com.qpyy.libcommon.bean.UserBean;
import com.qpyy.libcommon.event.RoomBeckoningEvent;
import com.qpyy.libcommon.utils.ImageUtils;
import com.qpyy.room.R;
import com.qpyy.room.R2;
import com.qpyy.room.bean.RoomPitBean;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.utils.view
 * 创建人 王欧
 * 创建时间 2020/4/9 12:46 PM
 * 描述 describe
 */
public class RoomDefaultWheatView extends BaseWheatView {

    @BindView(R2.id.iv_tag_boos)
    ImageView ivTagBoss;

    private boolean showCharm = true;

    public RoomDefaultWheatView(Context context) {
        this(context, null, 0);
    }

    public RoomDefaultWheatView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoomDefaultWheatView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void initPit(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoomDefaultWheatView);
        pitNumber = typedArray.getString(R.styleable.RoomDefaultWheatView_room_wheat_number);
        typedArray.recycle();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.room_view_default_wheat;
    }

    @Override
    protected void setPitData(RoomPitBean bean) {

        if (ObjectUtils.isEmpty(bean.getUser_id()) || "0".equals(bean.getUser_id())) {
            mTvName.setText("");
            if (showCharm) {  //魅力值打开
                if (isBoss()) {   //Boss位显示boss图标
                    mCharmView.setVisibility(View.INVISIBLE);
                    ivTagBoss.setVisibility(View.VISIBLE);
                } else {
                    mCharmView.setVisibility(View.VISIBLE);
                }
            } else {
                mCharmView.setVisibility(View.INVISIBLE);
                if (isBoss()) {
                    ivTagBoss.setVisibility(View.VISIBLE);
                }
            }
            //麦位上锁背景设置
            if (isBoss()) {
                ImageUtils.loadRes(isLocked() ? R.mipmap.room_wheat_islock : R.mipmap.room_ic_wheat_boss, mRiv);
            } else {
                ImageUtils.loadRes(isLocked() ? R.mipmap.room_wheat_islock : R.mipmap.room_ic_wheat_default, mRiv);
            }
            mIvSex.setVisibility(INVISIBLE);
            mIvFrame.setVisibility(INVISIBLE);
            mIvFace.remove();
            //停止声浪
            mIvRipple.stopAnimation();
            mIvRipple.setVisibility(INVISIBLE);
        } else {
            if (showCharm) {  //魅力值打开
                mCharmView.setVisibility(View.VISIBLE);
                ivTagBoss.setVisibility(View.INVISIBLE);
            } else {
                mCharmView.setVisibility(View.INVISIBLE);
                if (isBoss()) {
                    ivTagBoss.setVisibility(View.VISIBLE);
                }
            }
            //麦位上锁背景设置
            if (isBoss()) {
                ImageUtils.loadRes(isLocked() ? R.mipmap.room_wheat_islock : R.mipmap.room_ic_wheat_boss, mRiv);
            } else {
                ImageUtils.loadRes(isLocked() ? R.mipmap.room_wheat_islock : R.mipmap.room_ic_wheat_default, mRiv);
            }
            if (isBoss()) {
                mCharmView.setVisibility(View.VISIBLE);
                ivTagBoss.setVisibility(View.INVISIBLE);
            }
            //开启声浪
            mIvRipple.setVisibility(VISIBLE);
            mTvName.setText(bean.getNickname());
            ImageUtils.loadHeadCC(bean.getHead_picture(), mRiv);
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
        if (showCharm) {    //判断魅力值开关
            if (isBoss()) {
                if (isOn()) {
                    mCharmView.setVisibility(VISIBLE);
                    ivTagBoss.setVisibility(INVISIBLE);
                } else {
                    mCharmView.setVisibility(INVISIBLE);
                    ivTagBoss.setVisibility(VISIBLE);
                }
            } else {
                mCharmView.setVisibility(VISIBLE);
            }
        } else {
            mCharmView.setVisibility(INVISIBLE);
        }
    }

    /**
     * 心动值显示开关
     *
     * @param roomBeckoningEvent
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    @Override
    public void subscribeMessages(RoomBeckoningEvent roomBeckoningEvent) {
        if (roomId.equals(roomBeckoningEvent.getRoomId())) {
            showCharm = roomBeckoningEvent.isOpen();
            if (showCharm) {
                if (isBoss()) {
                    if (isOn()) {
                        mCharmView.setVisibility(VISIBLE);
                        ivTagBoss.setVisibility(INVISIBLE);
                    } else {
                        mCharmView.setVisibility(INVISIBLE);
                        ivTagBoss.setVisibility(VISIBLE);
                    }
                } else {
                    mCharmView.setVisibility(VISIBLE);
                }
            } else {
                mCharmView.setVisibility(INVISIBLE);
            }

        }
    }
}
