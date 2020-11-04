package com.qpyy.room.widget;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.text.Html;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.qpyy.libcommon.event.RoomGiftEvent;
import com.qpyy.libcommon.utils.ImageUtils;
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
 * 描述 小猫钓鱼飘窗
 */
public class BigGiftAnimView extends ConstraintLayout implements Animation.AnimationListener {
    @BindView(R2.id.iv_bg)
    ImageView mIvBg;
    @BindView(R2.id.iv_gift)
    ImageView mIvGift;
    @BindView(R2.id.tv_content)
    MarqueeTextView mTvContent;
    private Animation mAnimation;
    public boolean animEnded = true;

    public BigGiftAnimView(Context context) {
        this(context, null);
    }

    public BigGiftAnimView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.room_view_big_gift_anim, this);
        ButterKnife.bind(this);
        setVisibility(GONE);
        mAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.room_anim_set_big_gift);
        mAnimation.setFillAfter(true);
        mAnimation.setAnimationListener(this);
    }

    public void showAnim() {
        if (SpUtils.getOpenEffect() == 0) {
            return;
        }
        if (queue == null || queue.size() == 0) {
            return;
        }
        if (animEnded && isAttachedToWindow()) {
            RoomGiftEvent animBean = queue.poll();
            if (animBean != null) {
                ImageUtils.loadImageView(animBean.getGift().getGift_picture(), mIvGift);
                mTvContent.setText(Html.fromHtml(animBean.getGift().getText()));
            }
            setVisibility(VISIBLE);
            animEnded = false;
            this.startAnimation(mAnimation);
        }
    }

    public void addAnim(RoomGiftEvent bean) {
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

    private Queue<RoomGiftEvent> queue = new LinkedList<>();

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
