package com.qpyy.room.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.qpyy.libcommon.bean.RoomUserJoinModel;
import com.qpyy.libcommon.utils.ImageUtils;
import com.qpyy.libcommon.utils.LogUtils;
import com.qpyy.libcommon.utils.SpUtils;
import com.qpyy.libcommon.widget.NewView;
import com.qpyy.libcommon.widget.NobilityView;
import com.qpyy.libcommon.widget.RoleView;
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
 * 创建时间 2020/8/12 1:56 PM
 * 描述 describe
 */
public class WelcomeAnimView extends FrameLayout implements Animation.AnimationListener {
    @BindView(R2.id.tv_name)
    TextView mTvName;
    @BindView(R2.id.iv_rank)
    ImageView mIvRank;
    @BindView(R2.id.iv_nobility)
    NobilityView mIvNobility;
    @BindView(R2.id.iv_role)
    RoleView mIvRole;
    @BindView(R2.id.iv_new)
    NewView mIvNew;
    private boolean isFirst = true;
    private Animation mAnimation;
    public boolean animEnded = true;

    public WelcomeAnimView(Context context) {
        this(context, null);
    }

    public WelcomeAnimView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.room_view_welcome_anim_view, this);
        ButterKnife.bind(this);
        setVisibility(GONE);
        mAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.room_anim_set_welcome);
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
            RoomUserJoinModel animBean = queue.poll();
            if (animBean != null) {
//                mIvRole.setRole(animBean.getRole());
//                mIvNew.setVisibility(animBean.getUser_is_new() == 1 ? VISIBLE : GONE);
                mTvName.setText(animBean.getNickname());
//                mIvNobility.setVisibility(TextUtils.isEmpty(animBean.getNobility_icon()) ? GONE : VISIBLE);
//                ImageUtils.loadImageView(animBean.getNobility_icon(), mIvNobility);
                mIvRank.setVisibility(TextUtils.isEmpty(animBean.getRank_icon()) ? GONE : VISIBLE);
                ImageUtils.loadImageView(animBean.getRank_icon(), mIvRank);
            }
            setVisibility(VISIBLE);
            this.startAnimation(mAnimation);
        }
    }

    public void addAnim(RoomUserJoinModel bean) {
        if (SpUtils.getOpenEffect() == 0) {
            return;
        }

        if (queue.size() == 0) {
            queue.add(bean);
            showAnim();
        }else {
            queue.add(bean);
        }
    }

    private Queue<RoomUserJoinModel> queue = new LinkedList<>();

    @Override
    public void onAnimationStart(Animation animation) {
        animEnded = false;
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
    public void closeEffect() {
        //清空队列
        queue.clear();
        //关闭动画
        if (mAnimation != null && !animEnded) {
            mAnimation.cancel();
            mAnimation = null;
        }
    }

}
