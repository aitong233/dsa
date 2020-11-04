package com.qpyy.room.widget;

import android.content.Context;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.opensource.svgaplayer.SVGAImageView;
import com.orhanobut.logger.Logger;
import com.qpyy.libcommon.bean.FaceBean;
import com.qpyy.libcommon.bean.RoomClearCardiacAllModel;
import com.qpyy.libcommon.bean.RoomClearCardiacModel;
import com.qpyy.libcommon.bean.RoomClosePitModel;
import com.qpyy.libcommon.bean.RoomCountDownModel;
import com.qpyy.libcommon.bean.RoomDownWheatModel;
import com.qpyy.libcommon.bean.RoomGiveGiftModel;
import com.qpyy.libcommon.bean.RoomRollModel;
import com.qpyy.libcommon.bean.RoomWheatModel;
import com.qpyy.libcommon.bean.WheatVoiceModel;
import com.qpyy.libcommon.event.QiuGameEndEvent;
import com.qpyy.libcommon.event.QiuGameResultEvent;
import com.qpyy.libcommon.event.QiuGameStartEvent;
import com.qpyy.libcommon.event.RoomBanWheatEvent;
import com.qpyy.libcommon.event.RoomBeckoningEvent;
import com.qpyy.libcommon.event.RoomFaceEvent;
import com.qpyy.libcommon.utils.GiftAnimatorUtil;
import com.qpyy.libcommon.utils.ImageUtils;
import com.qpyy.libcommon.utils.LogUtils;
import com.qpyy.libcommon.utils.SpUtils;
import com.qpyy.libcommon.utils.ThreadPoolUtil;
import com.qpyy.libcommon.widget.ExpressionImgView;
import com.qpyy.libcommon.widget.GameImgView;
import com.qpyy.room.R;
import com.qpyy.room.R2;
import com.qpyy.room.bean.ClosePhone;
import com.qpyy.room.bean.RoomPitBean;
import com.qpyy.room.bean.WheatAnimEvent;
import com.qpyy.room.event.SoundLevelEvent;
import com.qpyy.room.listener.IBaseWheat;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public abstract class BaseWheatView extends ConstraintLayout implements IBaseWheat {

    @BindView(R2.id.riv)
    public CircleImageView mRiv;
    @BindView(R2.id.charm_view)
    public WheatCharmView mCharmView;
    @BindView(R2.id.tv_name)
    public TextView mTvName;
    @BindView(R2.id.iv_sex)
    public ImageView mIvSex;
    @BindView(R2.id.iv_frame)
    public SVGAImageView mIvFrame;
    @BindView(R2.id.iv_ripple)
    public SVGAImageView mIvRipple;
    @BindView(R2.id.iv_face)
    public ExpressionImgView mIvFace;
    @BindView(R2.id.game_imgview)
    public GameImgView gameImgView;
    @BindView(R2.id.iv_shutup)
    public ImageView mIvShutup;
    @BindView(R2.id.iv_gift)
    public ImageView mIvGift;

    public RoomPitBean pitBean;//麦位数据
    public String roomId;//房间id

    CountDownTimer mCountDownTimer;

    private boolean showGiftAnim = true;//显示麦位动画

    public static final String WHEAT_BOSS = "8";//老板位

    public static final String WHEAT_HOST = "9";//主持位

    public float oX;
    public float oY;

    boolean closePhone = true;//自己麦位关闭话筒，用于判断声纹显示

    public String pitNumber;

    public BaseWheatView(Context context) {
        this(context, null, 0);
    }

    public BaseWheatView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseWheatView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(getLayoutId(), this, true);
        ButterKnife.bind(this);
        setClipChildren(false);
        setClipToPadding(false);
        oX = mIvFace.getX();
        oY = mIvFace.getY();
        initPit(context, attrs);
    }

    protected abstract void initPit(Context context, AttributeSet attrs);

    protected abstract int getLayoutId();

    protected abstract void setPitData(RoomPitBean bean);

    /**
     * 设置麦位数据
     *
     * @param bean
     */
    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setData(RoomPitBean bean) {
        if (!pitNumber.equals(bean.getPit_number())) {
            return;
        }
        this.pitBean = bean;
        this.roomId = bean.getRoom_id();
        countDownTime(bean.getCount_down());
        setCardiac(pitBean.getXin_dong());
        setPitData(bean);
        if (isOn() && bean.getBall_state() == 1) {
            gameImgView.startGame();
        } else {
            gameImgView.overGame();
        }
        if (TextUtils.isEmpty(pitBean.getUser_id())) {
            pitBean.setUser_id("0");
        }

        closePhone = "0".equals(pitBean.getVoice());
        LogUtils.e("setData", "voice:" + pitBean.getVoice());
        LogUtils.e("setData", "closePhone:" + closePhone);
        LogUtils.e("setData", "userId:" + pitBean.getUser_id());
        //心动值
        //显示心动
        if ("1".equals(pitBean.getShutup())) {
            mIvShutup.setVisibility(VISIBLE);

        } else {
            mIvShutup.setVisibility(GONE);

        }
    }

    /**
     * 开始倒计时
     *
     * @param time
     */
    public void countDownTime(int time) {
        try {
            if (time <= 0) {
                mCharmView.setTime(0);
                releaseCountDownTimer();
                return;
            }
            releaseCountDownTimer();
            mCountDownTimer = new CountDownTimer(time * 1000L, 1000L) {
                @Override
                public void onTick(long millisUntilFinished) {
                    if (mCharmView != null) {
                        int time1 = (int) (millisUntilFinished / 1000);
                        pitBean.setCount_down(time1);
                        mCharmView.setTime(time1);
                    }
                }

                @Override
                public void onFinish() {
                    mCharmView.setTime(0);
                }
            };
            mCountDownTimer.start();
        } catch (Exception e) {
            Logger.e("countDownTime", e);
        }
    }


    @Override
    protected void onDetachedFromWindow() {
        releaseCountDownTimer();
        super.onDetachedFromWindow();
    }

    /**
     * 释放倒计时
     */
    private void releaseCountDownTimer() {
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
            mCountDownTimer = null;
        }
    }


    /**
     * 麦位是否有人
     *
     * @return
     */
    @Override
    public boolean isOn() {
        return pitBean != null && !TextUtils.isEmpty(pitBean.getUser_id()) && !"0".equals(pitBean.getUser_id());
    }

    /**
     * 显示麦位礼物动画
     *
     * @param listBean
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    @Override
    public void showGift(RoomGiveGiftModel.GiftListBean listBean) {
        if (!showGiftAnim) {
            mIvGift.setVisibility(GONE);
            return;
        }
        if (listBean.getUser_id() == null || !listBean.getUser_id().equals(pitBean.getUser_id())) {
            return;
        }
        ImageUtils.loadImageView(listBean.getPicture(), mIvGift);
        GiftAnimatorUtil.anim(mIvGift, oX, oY);
    }

    /**
     * 设置心动值
     *
     * @param rough_number
     */
    @Override
    public void setCardiac(String rough_number) {
        if (mCharmView != null) {
            pitBean.setXin_dong(rough_number);
            mCharmView.setSex(pitBean.getSex(), pitBean.getUser_id(), pitBean.getXin_dong());
        }
    }

    /**
     * 清空心动值
     */
    @Override
    public void clearCardiac() {
        if (mCharmView != null) {
            pitBean.setXin_dong("0");
            mCharmView.setSex(pitBean.getSex(), pitBean.getUser_id(), pitBean.getXin_dong());
        }
    }

    @Override
    public void register(Object obj) {
        EventBus.getDefault().register(this);
    }

    @Override
    public void unRegister(Object obj) {
        EventBus.getDefault().unregister(this);
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
            mCharmView.setVisibility(roomBeckoningEvent.isOpen() ? VISIBLE : INVISIBLE);
        }
    }

    /**
     * 开闭麦
     *
     * @param wheatVoiceModel
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void subscribeMessages(WheatVoiceModel wheatVoiceModel) {
        if (!roomId.equals(wheatVoiceModel.getRoom_id())) {
            return;
        }
        if (pitBean.getUser_id().equals(wheatVoiceModel.getUser_id())) {
            closePhone = wheatVoiceModel.getAction().equals("0");
            LogUtils.e("其他位置开闭麦", "voice:" + pitBean.getVoice() + " closePhone:" + closePhone + "+userId:" + pitBean.getUser_id());
        }
    }


    /**
     * 心动值变化
     *
     * @param cardiacListBean
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    @Override
    public void subscribeMessages(RoomGiveGiftModel.CardiacListBean cardiacListBean) {
        if (!roomId.equals(cardiacListBean.getRoom_id())) {
            return;
        }
        if (this.pitNumber.equals(cardiacListBean.getPit_number())) {
            this.setCardiac(cardiacListBean.getRough_number());
        }
    }

    /**
     * 清空单个麦位心动值
     *
     * @param roomClearCardiacModel
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    @Override
    public void subscribeMessages(RoomClearCardiacModel roomClearCardiacModel) {
        if (!roomId.equals(roomClearCardiacModel.getRoom_id())) {
            return;
        }
        if (this.pitNumber.equals(roomClearCardiacModel.getPit_number())) {
            clearCardiac();
        }

    }


    /**
     * 清空所有心动值
     *
     * @param roomClearCardiacAllModel
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    @Override
    public void subscribeMessages(RoomClearCardiacAllModel roomClearCardiacAllModel) {
        if (!roomId.equals(roomClearCardiacAllModel.getRoom_id())) {
            return;
        }
        clearCardiac();
    }

    /**
     * 上麦
     *
     * @param roomWheatModel
     */
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    @Override
    public void subscribeMessages(RoomWheatModel roomWheatModel) {
        EventBus.getDefault().removeStickyEvent(roomWheatModel);
        if (!roomId.equals(roomWheatModel.getRoom_id()) || !pitNumber.equals(roomWheatModel.getPit_number())) {
            return;
        }
        pitBean.setNickname(roomWheatModel.getNickname());
        pitBean.setHead_picture(roomWheatModel.getHead_picture());
        pitBean.setBanned(roomWheatModel.getBanned());
        pitBean.setUser_id(roomWheatModel.getUser_id());
        pitBean.setDress_picture(roomWheatModel.getDress_picture());
        pitBean.setSex(roomWheatModel.getSex());
        pitBean.setBall_state(roomWheatModel.getBall_state());
        setData(pitBean);
        LogUtils.e("上麦", "voice:" + pitBean.getVoice() + " closePhone:" + closePhone + "+userId:" + pitBean.getUser_id());
    }


    /**
     * 下麦
     *
     * @param roomDownWheatModel
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    @Override
    public void subscribeMessages(RoomDownWheatModel roomDownWheatModel) {
        if (!roomId.equals(roomDownWheatModel.getRoom_id()) || !pitNumber.equals(roomDownWheatModel.getPit_number())) {
            return;
        }
        pitBean.setUser_id("0");
        setData(pitBean);
        LogUtils.e("下麦", "voice:" + pitBean.getVoice() + " closePhone:" + closePhone + "+userId:" + pitBean.getUser_id());
    }

    /**
     * 倒计时
     *
     * @param roomCountDownModel
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    @Override
    public void subscribeMessages(RoomCountDownModel roomCountDownModel) {
        if (!roomId.equals(roomCountDownModel.getRoom_id()) || !pitNumber.equals(roomCountDownModel.getPit_number())) {
            return;
        }
        pitBean.setCount_down(roomCountDownModel.getSeconds());
        countDownTime(roomCountDownModel.getSeconds());
    }


    /**
     * 禁麦
     *
     * @param roomBanWheatEvent
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    @Override
    public void subscribeMessages(RoomBanWheatEvent roomBanWheatEvent) {
        if (!roomId.equals(roomBanWheatEvent.getRoomId()) || !pitNumber.equals(roomBanWheatEvent.getPit_number())) {
            return;
        }
        pitBean.setShutup(roomBanWheatEvent.isBanWheat() ? "1" : "2");
        setData(pitBean);
        LogUtils.e("禁麦", "voice:" + pitBean.getVoice() + " closePhone:" + closePhone + "+userId:" + pitBean.getUser_id());

    }

    /**
     * 麦位动画是否需要显示，onResume显示，onstop不显示
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void giftAnimEvent(WheatAnimEvent event) {
        showGiftAnim = event.show;
    }

    /**
     * 锁麦
     *
     * @param roomClosePitModel
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    @Override
    public void subscribeMessages(RoomClosePitModel roomClosePitModel) {
        if (!roomId.equals(roomClosePitModel.getRoom_id()) || !pitNumber.equals(roomClosePitModel.getPit_number())) {
            return;
        }
        pitBean.setState(roomClosePitModel.getAction());
        //麦位上锁
        setData(pitBean);
        LogUtils.e("锁麦", "voice:" + pitBean.getVoice() + " closePhone:" + closePhone + "+userId:" + pitBean.getUser_id());
    }


    /**
     * 声浪
     *
     * @param soundLevelEvent
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    @Override
    public void subscribeMessages(SoundLevelEvent soundLevelEvent) {
        if (soundLevelEvent.userId.equals(pitBean.getUser_id())) {
            if (soundLevelEvent.volume == 0) {
                mIvRipple.stopAnimation();
            } else {
                if (!mIvRipple.isAnimating()) {
                    mIvRipple.startAnimation();
                }
            }
            if (closePhone) {
                mIvRipple.stopAnimation();
            }
        }
    }

//    /**
//     * 用户关闭麦克风
//     *
//     * @param closePhone
//     */
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void subscribeMessages(ClosePhone closePhone) {
//
//        if (SpUtils.getUserId().equals(pitBean.getUser_id())) {
//            this.closePhone = closePhone.isClosePhone();
//            LogUtils.e("用户关闭麦克风", "voice:" + pitBean.getVoice() + " closePhone:" + closePhone + "+userId:" + pitBean.getUser_id());
//        }
//    }

    /**
     * 麦位是否被锁
     *
     * @return
     */
    @Override
    public boolean isLocked() {
        return !isOn() && "1".equals(pitBean.getState());
    }

    /**
     * 表情
     *
     * @param roomFaceEvent
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    @Override
    public void subscribeMessages(RoomFaceEvent roomFaceEvent) {
        if (!roomId.equals(roomFaceEvent.getRoom_id()) || !pitNumber.equals(roomFaceEvent.getPit_number())) {
            return;
        }
        mIvFace.addData(new FaceBean(roomFaceEvent.getSpecial(), 1));
    }

    /**
     * 抽签
     *
     * @param roomRollModel
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    @Override
    public void subscribeMessages(RoomRollModel roomRollModel) {
        if (!roomId.equals(roomRollModel.getRoom_id()) || !pitNumber.equals(roomRollModel.getPit_number())) {
            return;
        }


        mIvFace.addData(new FaceBean(roomRollModel.getNumber(), 2));
    }

    /**
     * 球球大作战开球
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    @Override
    public void subscribeMessages(QiuGameStartEvent event) {
        if (roomId.equals(event.getRoom_id()) && pitNumber.equals(event.getPit_number())) {
            gameImgView.startGame();
        }
    }

    /**
     * 球球大作战弃球
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    @Override
    public void subscribeMessages(QiuGameEndEvent event) {
        if (roomId.equals(event.getRoom_id()) && pitNumber.equals(event.getPit_number())) {
            gameImgView.overGame();
        }
    }

    /**
     * 球球大作战开球
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    @Override
    public void subscribeMessages(QiuGameResultEvent event) {
        if (roomId.equals(event.getRoom_id()) && pitNumber.equals(event.getPit_number())) {
            gameImgView.setGameResult(event.getFirst(), event.getSecond(), event.getThird());
        }
    }

    /**
     * 是否主持
     *
     * @return
     */
    @Override
    public boolean isHost() {
        return WHEAT_HOST.equals(pitNumber);
    }

    public boolean isBoss() {
        return WHEAT_BOSS.equals(pitNumber);
    }
}
