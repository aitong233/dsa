package com.spadea.xqipao.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.LinearLayout;

import com.spadea.yuyin.R;
import com.spadea.yuyin.util.utilcode.ScreenUtils;

public class DropView extends LinearLayout {
    public DropView(Context context) {
        super(context);
        init();
    }

    public DropView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DropView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    void init() {
        post(new Runnable() {
            @Override
            public void run() {
                //设置初始位置
                int sh = ScreenUtils.getScreenHeight();
                int sw = ScreenUtils.getScreenWidth();
                setBackgroundResource(R.drawable.bg_home_drop_view);
                int y = (int) (0.8f * sh) - getHeight();
                int x = sw - getWidth();
                setTranslationX(x);
                setTranslationY(y);
            }
        });
    }


    boolean starDrap = false;
    float X1;
    float X2;
    float Y1;
    float Y2;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (starDrap) return true;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                X1 = event.getX();
                Y1 = event.getY();
                break;

            case MotionEvent.ACTION_MOVE:
                X2 = event.getX();//当手指抬起时，再次获取屏幕位置的X值
                Y2 = event.getY();//同理
                Action(X1, X2, Y1, Y2);
                break;


        }
        return starDrap;
    }

    String TAG = "DropView";

    public boolean Action(float X1, float X2, float Y1, float Y2) {
        float ComparedX = X2 - X1;//第二次的X坐标的位置减去第一次X坐标的位置，代表X坐标上的变化情况
        float ComparedY = Y2 - Y1;//同理
        //当X坐标的变化量的绝对值大于Y坐标的变化量的绝对值，以X坐标的变化情况作为判断依据
        //上下左右的判断，都在一条直线上，但手指的操作不可能划直线，所有选择变化量大的方向上的量
        //作为判断依据
        if (Math.abs(ComparedX) > 30 || Math.abs(ComparedY) > 30) {
            Log.i(TAG, "Action: 拖动");
            starDrap = true;
            setBackgroundResource(R.drawable.bg_home_drop_view);
            return true;
        } else {
            starDrap = false;
            return false;
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {

            case MotionEvent.ACTION_MOVE:
                setBackgroundResource(R.drawable.bg_home_drop_view);
                setTranslationX(getX() + (event.getX() - X1));
                setTranslationY(getY() + (event.getY() - Y1));
                X2 = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                starDrap = false;
                int sw = ScreenUtils.getScreenWidth();
                Log.i(TAG, "onTouchEvent: " + sw + "," + X2);
                boolean isR = getTranslationX() + getWidth() / 2 >= sw / 2;//贴边方向
                ObjectAnimator anim = ObjectAnimator.ofFloat(this, "translationX", isR ? sw - getWidth() : 0f).setDuration(200);
                anim.start();

                break;

        }

        return true;
    }


    public void doRevealAnimation(View mPuppet, boolean flag) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            int[] vLocation = new int[2];
            getLocationInWindow(vLocation);
            int centerX = vLocation[0] + getMeasuredWidth() / 2;
            int centerY = vLocation[1] + getMeasuredHeight() / 2;

            int height = ScreenUtils.getScreenHeight();
            int width = ScreenUtils.getScreenWidth();
            int maxRradius = (int) Math.hypot(height, width);
            Log.e("hei", maxRradius + "");

            if (flag) {
                mPuppet.setVisibility(VISIBLE);
                Animator animator = ViewAnimationUtils.createCircularReveal(mPuppet, centerX, centerY, maxRradius, 0);
                animator.setDuration(600);
                animator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        mPuppet.setVisibility(View.GONE);
                    }
                });
                animator.start();
                flag = false;
            } else {
                Animator animator = ViewAnimationUtils.createCircularReveal(mPuppet, centerX, centerY, 0, maxRradius);
                animator.setDuration(1000);
                animator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        mPuppet.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                animator.start();
                flag = true;
            }
        }
    }


}
