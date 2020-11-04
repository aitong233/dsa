package com.qpyy.libcommon.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.qpyy.libcommon.widget.animator.ExplosionField;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class GiftAnimatorUtil {


    public static ObjectAnimator tada(View view) {
        return tada(view, 1f);
    }

    public static void tadaAnim(View view) {
        ObjectAnimator tada = tada(view, 1f);
        tada.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                new ExplosionField(view.getContext()).explode(view, new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        view.setVisibility(GONE);
                    }
                });
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        tada.start();
    }

    public static void anim(View view, float oX, float oY) {
        if (SpUtils.getOpenEffect() == 0) {
            return;
        }
        view.setVisibility(VISIBLE);
        view.setTranslationX(0);
        view.setTranslationY(0);
        ObjectAnimator tada = tada(view);
        PropertyValuesHolder objectAnimatorY = PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, ScreenUtils.getScreenHeight(), oY);
        int x = ScreenUtils.getScreenWidth() / 2;
        PropertyValuesHolder objectAnimatorX = PropertyValuesHolder.ofFloat(View.TRANSLATION_X, x, oX);
        ObjectAnimator valueAnimator = ObjectAnimator.ofPropertyValuesHolder(view, objectAnimatorY, objectAnimatorX).
                setDuration(500);
        AnimatorSet set = new AnimatorSet();
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                if (Math.abs(view.getY() - oY) < ConvertUtils.dp2px(50)) {
                    new ExplosionField(view.getContext()).explode(view, new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            view.setVisibility(GONE);
                        }
                    });
                } else {
                    view.setVisibility(GONE);
                }
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                view.setVisibility(GONE);
            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        set.playSequentially(valueAnimator, tada);
        set.start();
    }


    public static ObjectAnimator tada(View view, float shakeFactor) {
        PropertyValuesHolder pvhScaleX = PropertyValuesHolder.ofKeyframe(View.SCALE_X,
                Keyframe.ofFloat(0f, 1f),
                Keyframe.ofFloat(.1f, .9f),
                Keyframe.ofFloat(.2f, .9f),
                Keyframe.ofFloat(.3f, 1.1f),
                Keyframe.ofFloat(.4f, 1.1f),
                Keyframe.ofFloat(.5f, 1.1f),
                Keyframe.ofFloat(.6f, 1.1f),
                Keyframe.ofFloat(.7f, 1.1f),
                Keyframe.ofFloat(.8f, 1.1f),
                Keyframe.ofFloat(.9f, 1.1f),
                Keyframe.ofFloat(1f, 1f)
        );

        PropertyValuesHolder pvhScaleY = PropertyValuesHolder.ofKeyframe(View.SCALE_Y,
                Keyframe.ofFloat(0f, 1f),
                Keyframe.ofFloat(.1f, .9f),
                Keyframe.ofFloat(.2f, .9f),
                Keyframe.ofFloat(.3f, 1.1f),
                Keyframe.ofFloat(.4f, 1.1f),
                Keyframe.ofFloat(.5f, 1.1f),
                Keyframe.ofFloat(.6f, 1.1f),
                Keyframe.ofFloat(.7f, 1.1f),
                Keyframe.ofFloat(.8f, 1.1f),
                Keyframe.ofFloat(.9f, 1.1f),
                Keyframe.ofFloat(1f, 1f)
        );

        PropertyValuesHolder pvhRotate = PropertyValuesHolder.ofKeyframe(View.ROTATION,
                Keyframe.ofFloat(0f, 0f),
                Keyframe.ofFloat(.1f, -3f * shakeFactor),
                Keyframe.ofFloat(.2f, -3f * shakeFactor),
                Keyframe.ofFloat(.3f, 3f * shakeFactor),
                Keyframe.ofFloat(.4f, -3f * shakeFactor),
                Keyframe.ofFloat(.5f, 3f * shakeFactor),
                Keyframe.ofFloat(.6f, -3f * shakeFactor),
                Keyframe.ofFloat(.7f, 3f * shakeFactor),
                Keyframe.ofFloat(.8f, -3f * shakeFactor),
                Keyframe.ofFloat(.9f, 3f * shakeFactor),
                Keyframe.ofFloat(1f, 0)
        );

        return ObjectAnimator.ofPropertyValuesHolder(view, pvhScaleX, pvhScaleY, pvhRotate).
                setDuration(1000);
    }

}
