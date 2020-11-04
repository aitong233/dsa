package com.spadea.xqipao.utils.view.room.approach;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.BounceInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hyphenate.easeui.utils.view.GradeView;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.utilcode.ConvertUtils;
import com.spadea.xqipao.utils.ColorUtil;

import java.util.LinkedList;
import java.util.Queue;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WelcomeView extends LinearLayout implements Animator.AnimatorListener {


    private static Queue<WelcomeBean> queue = new LinkedList<>();
    @BindView(R.id.grade_view)
    GradeView mGradeView;
    @BindView(R.id.text)
    TextView mText;
    @BindView(R.id.ll)
    LinearLayout mLl;

    public WelcomeView(Context context) {
        super(context);
        initView(context);
    }

    public WelcomeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public WelcomeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_welcome, this, true);
        ButterKnife.bind(this);

    }

    public void initQueue() {
        queue = new LinkedList<>();
    }

    public void emptyQueue() {
        if (queue != null) {
            queue.clear();
            queue = null;
        }
    }


    public void loadGift(WelcomeBean userBean) {
        addGift(userBean);
    }

    private void addGift(WelcomeBean approachBean) {
        if (queue == null) {
            return;
        }
        if (queue.size() == 0) {
            queue.add(approachBean);
            showGift();
        } else {
            queue.add(approachBean);
        }
    }

    private void showGift() {
        if (isEmpty()) return;
        WelcomeBean bean = getGift();
        if (bean != null) {
            mLl.setVisibility(VISIBLE);
            mGradeView.setGrade(bean.rankId, bean.rankName);
            mGradeView.setVisibility(bean.rankId > 0 ? VISIBLE : GONE);
            mText.setText(bean.userName);
            mText.setTextColor(ColorUtil.getColorWithRankId(bean.rankId));
            GradientDrawable drawable = new GradientDrawable();
            drawable.setAlpha(128);
            drawable.setColor(ColorUtil.getColorWithRankId(bean.rankId));
            drawable.setCornerRadius(ConvertUtils.dp2px(30));
            mLl.setBackground(drawable);
            AnimatorSet animatorSet = new AnimatorSet();
            ObjectAnimator animator1 = ObjectAnimator.ofFloat(mLl, "translationX", 0).setDuration(500);
            ObjectAnimator animator2 = ObjectAnimator.ofFloat(mLl, "alpha", 1, 0).setDuration(2000);
            animator1.setInterpolator(new BounceInterpolator());
            animatorSet.playSequentially(animator1, animator2);
            animatorSet.addListener(this);
            animatorSet.start();
        }
    }

    public WelcomeBean getGift() {
        WelcomeBean welcomeBean = null;
        if (!isEmpty()) {
            // 获取队列首个礼物实体
            welcomeBean = queue.poll();
            // 移除队列首个礼物实体
        }
        return welcomeBean;
    }

    /**
     * 礼物是否为空
     */
    public boolean isEmpty() {
        return queue == null || queue.size() == 0;
    }


    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {
        mLl.setVisibility(INVISIBLE);
        showGift();
    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }

    public static class WelcomeBean {
        public String userName;
        public int rankId;
        public String rankName;

        public WelcomeBean(String userName, int rankId, String rankName) {
            this.userName = userName;
            this.rankId = rankId;
            this.rankName = rankName;
        }
    }
}
