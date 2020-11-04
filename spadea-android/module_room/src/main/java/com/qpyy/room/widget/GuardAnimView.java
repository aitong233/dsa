package com.qpyy.room.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.qpyy.libcommon.bean.RoomStarModel;
import com.qpyy.libcommon.event.RoomGuardEvent;
import com.qpyy.libcommon.utils.SpUtils;
import com.qpyy.libcommon.widget.MarqueeTextView;
import com.qpyy.room.R;
import com.qpyy.room.R2;

import java.util.LinkedList;
import java.util.Queue;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.widget
 * 创建人 王欧
 * 创建时间 2020/8/12 10:01 AM
 * 描述 黄金守护飘窗
 */
public class GuardAnimView extends ConstraintLayout implements Animation.AnimationListener {
    @BindView(R2.id.iv_bg)
    ImageView mIvBg;
    @BindView(R2.id.tv_content)
    MarqueeTextView mTvContent;
    private Animation mAnimation;
    public boolean animEnded = true;

    public GuardAnimView(Context context) {
        this(context, null);
    }

    public GuardAnimView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.room_view_guard_anim, this);
        ButterKnife.bind(this);
        setVisibility(GONE);
        mAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.room_anim_set_guard);
        mAnimation.setFillAfter(true);
        mAnimation.setAnimationListener(this);
    }

    public void showAnim() {
        if (SpUtils.getOpenEffect() == 0) {//特效关闭
            return;
        }
        if (queue == null || queue.size() == 0) {
            return;
        }
        if (animEnded && isAttachedToWindow()) {
            Object obj = queue.poll();
            if (obj instanceof RoomGuardEvent) {
                RoomGuardEvent animBean = (RoomGuardEvent) obj;
                mIvBg.setBackgroundResource(R.mipmap.room_bg_guard_anim);
                mTvContent.setText(String.format("%s给%s开通了黄金守护", animBean.getNickname_from(), animBean.getNickname_to()));
                mTvContent.setTextColor(Color.WHITE);
            } else if (obj instanceof RoomStarModel) {
                RoomStarModel animBean = (RoomStarModel) obj;
                if (animBean.getRank() == 1) {
                    mIvBg.setBackgroundResource(R.mipmap.room_bg_week_star_rank1);
                } else if (animBean.getRank() == 2) {
                    mIvBg.setBackgroundResource(R.mipmap.room_bg_week_star_rank2);
                } else if (animBean.getRank() == 3) {
                    mIvBg.setBackgroundResource(R.mipmap.room_bg_week_star_rank3);
                }
                mTvContent.setTextColor(Color.parseColor("#f6ff00"));
                mTvContent.setText(String.format("欢迎%s进入房间", animBean.getNickname()));
            }
            setVisibility(VISIBLE);
            animEnded = false;
            this.startAnimation(mAnimation);
        }
    }

    public void addAnim(Object bean) {
        if (SpUtils.getOpenEffect() == 0) {
            return;
        }
        if (queue.size() == 0) {
            queue.add(bean);
            showAnim();
        } else {
            queue.add(bean);
        }
    }

    private Queue<Object> queue = new LinkedList<>();

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        animEnded = true;
        postDelayed(new Runnable() {
            @Override
            public void run() {
                showAnim();
            }
        }, 200);

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    /**
     * 关闭特效
     */
    public void closeEffect(){
        //清空队列
        queue.clear();
        //关闭动画
        if(mAnimation !=null && !animEnded){
            mAnimation.cancel();
            mAnimation = null;
        }
    }
}
