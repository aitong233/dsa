package com.spadea.xqipao.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.os.Vibrator;
import android.support.animation.DynamicAnimation;
import android.support.animation.FloatPropertyCompat;
import android.support.animation.SpringAnimation;
import android.support.animation.SpringForce;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;

import com.spadea.yuyin.MyApplication;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.utilcode.BarUtils;
import com.spadea.xqipao.utils.StatusBarUtil;

import java.util.Random;

import static android.animation.ValueAnimator.INFINITE;
import static android.animation.ValueAnimator.REVERSE;
import static android.content.Context.VIBRATOR_SERVICE;


public class Anim {
    public static boolean animAlpha(View v) {
        if (v.getVisibility() != View.VISIBLE) {
            v.startAnimation(AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha_in));
            v.setVisibility(View.VISIBLE);
            return true;
        } else {
            v.startAnimation(AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha_out));
            v.setVisibility(View.GONE);
            return false;
        }
    }


    public static void alphaIn(View v) {
        if (v.getVisibility() != View.VISIBLE) {
            Animation an = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha_in);
            v.setVisibility(View.VISIBLE);
            an.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    //v.setAlpha(1);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            v.startAnimation(an);
        } else {
            v.setAlpha(1);
        }
    }

    public static void alphaOut(View v) {
        if (v.getVisibility() == View.VISIBLE) {
            Animation an = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha_out);
            an.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    // v.setAlpha(0);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            v.startAnimation(an);
            v.setVisibility(View.INVISIBLE);
        }
    }

    public static void leftIn(View v) {
        if (v.getVisibility() != View.VISIBLE) {
            Animation an = AnimationUtils.loadAnimation(v.getContext(), R.anim.pp_left_in);
            v.startAnimation(an);
            v.setVisibility(View.VISIBLE);
        }
    }

    public static void leftOut(View v) {
        if (v.getVisibility() == View.VISIBLE) {
            Animation an = AnimationUtils.loadAnimation(v.getContext(), R.anim.pp_left_out);
            v.startAnimation(an);
            v.setVisibility(View.GONE);
        }
    }

    public static void rightIn(View v) {
        if (v.getVisibility() != View.VISIBLE) {
            Animation an = AnimationUtils.loadAnimation(v.getContext(), R.anim.pp_right_in);
            v.startAnimation(an);
            v.setVisibility(View.VISIBLE);
        }
    }

    public static void rightOut(View v) {
        if (v.getVisibility() == View.VISIBLE) {
            Animation an = AnimationUtils.loadAnimation(v.getContext(), R.anim.pp_right_out);
            v.startAnimation(an);
            v.setVisibility(View.GONE);
        }
    }

    public static void bIn(View v) {
        if (v.getVisibility() != View.VISIBLE) {
            Animation an = AnimationUtils.loadAnimation(v.getContext(), R.anim.pp_bottom_in);
            v.startAnimation(an);
            v.setVisibility(View.VISIBLE);
        }
    }

    public static void bOut(View v) {
        if (v.getVisibility() == View.VISIBLE) {
            Animation an = AnimationUtils.loadAnimation(v.getContext(), R.anim.pp_bottom_out);
            v.startAnimation(an);
            v.setVisibility(View.GONE);
        }
    }


    public static void scaleIn(View v) {
        if (v.getVisibility() != View.VISIBLE) {
            v.startAnimation(AnimationUtils.loadAnimation(v.getContext(), R.anim.scale_in));
            v.setVisibility(View.VISIBLE);
        }
    }

    public static void scaleOut(View v) {
        if (v.getVisibility() == View.VISIBLE) {
            v.startAnimation(AnimationUtils.loadAnimation(v.getContext(), R.anim.scale_out));
            v.setVisibility(View.GONE);
        }
    }

    //旋转
//    public static void roate(View v){
//        v.startAnimation(AnimationUtils.loadAnimation(v.getContext(), R.anim.roate));
//    }


    public static void scalePop(View v) {
        v.startAnimation(AnimationUtils.loadAnimation(v.getContext(), R.anim.scale_pop));
    }

    public static void animShake(View view) {
        // 0.9f, 1.1f, 10f, 1000
        float scaleSmall = 0.9f;
        float scaleLarge = 1.1f;
        float shakeDegrees = 10f;
        long duration = 1000;
        if (view == null) {
            return;
        }
        //TODO 验证参数的有效性

        //由小变大
        //Animation scaleAnim = new ScaleAnimation(scaleSmall, scaleLarge, scaleSmall, scaleLarge);
        //从左向右
        Animation rotateAnim = new RotateAnimation(-shakeDegrees, shakeDegrees, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        //scaleAnim.setDuration(duration);
        rotateAnim.setDuration(duration / 10);
        rotateAnim.setRepeatMode(Animation.REVERSE);
        rotateAnim.setRepeatCount(10);
        AnimationSet smallAnimationSet = new AnimationSet(false);
        //smallAnimationSet.addAnimation(scaleAnim);
        smallAnimationSet.addAnimation(rotateAnim);
        view.startAnimation(smallAnimationSet);
    }

    public static void test(View view) {
        //创建两个弹性动画对象
        SpringAnimation springAnimation0 = null;
        SpringAnimation springAnimation1 = null;

//指定处理view的哪个属性，以及view此属性的最终值(2.0f)
        springAnimation0 = new SpringAnimation(view, new FloatPropertyCompat<View>("scaleX") {
            @Override
            public float getValue(View object) {
                float scaleX = object.getScaleX();
                return scaleX;
            }

            @Override
            public void setValue(View object, float value) {
                object.setScaleX(value);
            }
        }, 2.0f);
        springAnimation1 = new SpringAnimation(view, new FloatPropertyCompat<View>("scaleY") {
            @Override
            public float getValue(View object) {
                float scaleY = object.getScaleY();
                return scaleY;
            }

            @Override
            public void setValue(View object, float value) {
                object.setScaleY(value);
            }
        }, 2.0f);

//作一些设置，要不然肉眼看不出来生效
        springAnimation0.setMinimumVisibleChange(DynamicAnimation.MIN_VISIBLE_CHANGE_ALPHA);
        springAnimation1.setMinimumVisibleChange(DynamicAnimation.MIN_VISIBLE_CHANGE_ALPHA);

//指定此弹性动画的弹性阻尼
        springAnimation0.getSpring().setDampingRatio(SpringForce.DAMPING_RATIO_HIGH_BOUNCY);
//指定此弹性动画的弹性生硬度
        springAnimation0.getSpring().setStiffness(SpringForce.STIFFNESS_VERY_LOW);

//如上
        springAnimation1.getSpring().setDampingRatio(SpringForce.DAMPING_RATIO_HIGH_BOUNCY);
        springAnimation1.getSpring().setStiffness(SpringForce.STIFFNESS_VERY_LOW);

//启动动画
        springAnimation0.start();
        springAnimation0.start();
    }


    public static void bottomIn(View view) {
        if (view == null) return;
        view.setTranslationY(500f);
        view.setAlpha(0f);
        final SpringAnimation descTitleAnimY = new SpringAnimation(view, DynamicAnimation.TRANSLATION_Y, 0);
        descTitleAnimY.getSpring().setStiffness(SpringForce.STIFFNESS_VERY_LOW);
        descTitleAnimY.getSpring().setDampingRatio(SpringForce.DAMPING_RATIO_LOW_BOUNCY);
        final ValueAnimator descTitleAlphaAnim = ObjectAnimator.ofFloat(0f, 1f);
        descTitleAlphaAnim.setDuration(300);
        descTitleAlphaAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                view.setAlpha((Float) valueAnimator.getAnimatedValue());
            }
        });
        descTitleAlphaAnim.start();
        descTitleAnimY.start();
    }

    public static void rightPop(View view, int dp) {
        if (view == null) return;
        view.setTranslationX(dp);
        final SpringAnimation descTitleAnimY = new SpringAnimation(view, DynamicAnimation.TRANSLATION_X, 0);
        descTitleAnimY.getSpring().setStiffness(SpringForce.STIFFNESS_VERY_LOW);
        descTitleAnimY.getSpring().setDampingRatio(SpringForce.DAMPING_RATIO_LOW_BOUNCY);
        descTitleAnimY.start();
    }

    public static void XPop(View view) {
        if (view == null) return;
        SpringAnimation descTitleAnimY = new SpringAnimation(view, DynamicAnimation.TRANSLATION_X, 0);
        descTitleAnimY.getSpring().setStiffness(SpringForce.STIFFNESS_VERY_LOW);
        descTitleAnimY.getSpring().setDampingRatio(SpringForce.DAMPING_RATIO_LOW_BOUNCY);
        descTitleAnimY.start();
    }


    public static void bottomIn(View view, int dl) {
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                bottomIn(view);
            }
        }, dl);
    }

    public static void vibrator(long time) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            Vibrator vibrator = (Vibrator) MyApplication.getContext().getSystemService(VIBRATOR_SERVICE);
            vibrator.vibrate(time);
        }
    }


    public static void hearbeat(View v) {
        AnimatorSet all = new AnimatorSet();

        AnimatorSet set1 = new AnimatorSet();
        set1.playTogether(
                ObjectAnimator.ofFloat(v, "scaleX", 1, 0.6f)
                        .setDuration(500),
                ObjectAnimator.ofFloat(v, "scaleY", 1, 0.6f)
                        .setDuration(500)
        );

        AnimatorSet set2 = new AnimatorSet();
        set2.playTogether(
                ObjectAnimator.ofFloat(v, "scaleX", 0.6f, 1)
                        .setDuration(500),
                ObjectAnimator.ofFloat(v, "scaleY", 0.6f, 1)
                        .setDuration(500)
        );


        all.playSequentially(set1, set2);
        all.start();


    }


    public static void light(View v) {
        AnimatorSet set1 = new AnimatorSet();
        set1.playSequentially(
                ObjectAnimator.ofFloat(v, "alpha", 0, 1f)
                        .setDuration(200),
                ObjectAnimator.ofFloat(v, "alpha", 1f, 0.5f)
                        .setDuration(200),
                ObjectAnimator.ofFloat(v, "alpha", 0.5f, 0)
                        .setDuration(200)
        );
        set1.setInterpolator(new LinearInterpolator());
        set1.start();
    }


    public static void jump(View v) {
        AnimatorSet set1 = new AnimatorSet();
        set1.playSequentially(
                ObjectAnimator.ofFloat(v, "translationY", 0, -30)
                        .setDuration(200),
                ObjectAnimator.ofFloat(v, "translationY", -30, 0)
                        .setDuration(200)
        );
        set1.setInterpolator(new LinearInterpolator());
        set1.start();
    }

    static float[] scales = {1.3f,1.6f,1.5f,1.3f,1.5f,1.5f,2f,1.8f,1.2f};
    public static void throwV(View v,int sx,int sy,int tx,int ty) {
        v.setX(sx);v.setY(sy);
        SpringAnimation X = new SpringAnimation(v, DynamicAnimation.X, tx);
        X.getSpring().setStiffness(SpringForce.STIFFNESS_VERY_LOW);
        X.getSpring().setDampingRatio(SpringForce.DAMPING_RATIO_LOW_BOUNCY);

        SpringAnimation Y = new SpringAnimation(v, DynamicAnimation.Y, ty);
        Y.getSpring().setStiffness(SpringForce.STIFFNESS_VERY_LOW);
        Y.getSpring().setDampingRatio(SpringForce.DAMPING_RATIO_LOW_BOUNCY);

        AnimatorSet set1 = new AnimatorSet();
        float fl=scales[new Random().nextInt(scales.length)];
        set1.playTogether(
//                ObjectAnimator.ofFloat(v, "X", sx, tx)
//                        .setDuration(888),
//                ObjectAnimator.ofFloat(v, "Y", sy, ty)
//                        .setDuration(888),
                ObjectAnimator.ofFloat(v, "scaleX", 0.2f,fl )
                        .setDuration(600),
                ObjectAnimator.ofFloat(v, "scaleY", 0.2f, fl)
                        .setDuration(600),
//                ObjectAnimator.ofFloat(v, "rotation", 0f, 360f)
//                        .setDuration(888),
                ObjectAnimator.ofFloat(v, "alpha", 0f, 1)
                        .setDuration(366)

        );
        set1.addListener(new AnimatorListenerAdapter(){
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                v.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        AnimatorSet set = new AnimatorSet();
                        set.playTogether(
                                ObjectAnimator.ofFloat(v, "Alpha", 1f, 0f).setDuration(1200)
                        );
                        set.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                ViewGroup parent = (ViewGroup)v.getParent();
                                if (parent != null && parent instanceof ViewGroup) {
                                    parent.removeView(v);
                                }
                            }
                        });
                        set.setInterpolator(new LinearInterpolator());
                        set.start();
                    }
                },666);

            }
        });
        set1.setInterpolator(new LinearInterpolator());
        set1.start();
        X.start();
        Y.start();
    }


    public static void initBar(View bg,boolean drak) {
        StatusBarUtil.setTranslucentStatus((Activity) bg.getContext());
        if(bg!=null)StatusBarUtil.initTransP(bg, 5);
        BarUtils.setAndroidNativeLightStatusBar((Activity) bg.getContext(), drak);
    }

    public static void initBar(Context c, boolean drak) {
        StatusBarUtil.setTranslucentStatus((Activity)c);
        BarUtils.setAndroidNativeLightStatusBar((Activity)c, drak);
    }

    public static void shakeAnimation(View v,int counts) {
//        Animation translateAnimation = new TranslateAnimation(0, 10, 0, 0);
//        translateAnimation.setInterpolator(new CycleInterpolator(counts));
//        translateAnimation.setRepeatCount(1);
//        translateAnimation.setDuration(300);
//        v.startAnimation(translateAnimation);

        Animation rotateAnimation = new RotateAnimation(0f,10f,Animation.RELATIVE_TO_SELF,
                0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        rotateAnimation.setInterpolator(new CycleInterpolator(counts));
        rotateAnimation.setRepeatCount(1);
        rotateAnimation.setDuration(300);
        v.startAnimation(rotateAnimation);

    }

    public static void shakeVertical(View v) {
        Animation translateAnimation = new TranslateAnimation(0, 0, 0, 10);
        //translateAnimation.setInterpolator(new CycleInterpolator(2));
        translateAnimation.setRepeatCount(Animation.INFINITE);
        translateAnimation.setDuration(500);
        translateAnimation.setRepeatMode(Animation.REVERSE);
        v.startAnimation(translateAnimation);
    }


    public static void twinkleAnim(View v){
        ValueAnimator anim = ObjectAnimator.ofFloat(v, "alpha",0f, 1f);
        anim.setDuration(1200);
        anim.setRepeatCount(INFINITE);
        anim.setRepeatMode(REVERSE);
        anim.start();
    }


    public static void imgScale(View img){
        ObjectAnimator s1 = ObjectAnimator.ofFloat(img, "scaleX", 1f, 1.3f).setDuration(18000);
        ObjectAnimator s2 = ObjectAnimator.ofFloat(img, "scaleY", 1f, 1.3f).setDuration(18000);
        s1.setRepeatMode(ValueAnimator.REVERSE);
        s1.setRepeatCount(ValueAnimator.INFINITE);
        s2.setRepeatMode(ValueAnimator.REVERSE);
        s2.setRepeatCount(ValueAnimator.INFINITE);
        s1.start();
        s2.start();
    }


    public static void tabSel(View v){
        ViewCompat.animate(v).scaleY(1.2f).scaleX(1.2f).setDuration(500).start();
        ViewCompat.animate(v).alpha(1).setDuration(500).start();
    }

    public static void tabUnSel(View v){
        ViewCompat.animate(v).scaleY(1f).scaleX(1f).setDuration(500).start();
        ViewCompat.animate(v).alpha(0.6f).setDuration(500).start();
    }


    public static void tabSelA(View v){
        //ViewCompat.animate(v).scaleY(1.2f).scaleX(1.2f).setDuration(500).start();
        ViewCompat.animate(v).alpha(1).setDuration(500).start();
    }

    public static void tabUnSelA(View v){
        //ViewCompat.animate(v).scaleY(1f).scaleX(1f).setDuration(500).start();
        ViewCompat.animate(v).alpha(0.6f).setDuration(500).start();
    }


    public static void moveTo(View v,float sx,float sy,float ex,float ey) {
        Log.i("moveTo", "moveTo: "+sx+","+sy+","+ex+","+ey);
        AnimatorSet all = new AnimatorSet();

        AnimatorSet set1 = new AnimatorSet();
        set1.playTogether(
                ObjectAnimator.ofFloat(v, "scaleX", 0, 1.5f)
                        .setDuration(1500),
                ObjectAnimator.ofFloat(v, "scaleY", 0, 1.5f)
                        .setDuration(1500),
                ObjectAnimator.ofFloat(v, "alpha", 0, 1f)
                        .setDuration(600)
        );

        AnimatorSet set2 = new AnimatorSet();
        set2.playTogether(
                ObjectAnimator.ofFloat(v, "X", sx, ex)
                        .setDuration(1500),
                ObjectAnimator.ofFloat(v, "Y", sy, ey)
                        .setDuration(1500)
        );

        all.setInterpolator(new AnticipateOvershootInterpolator() );
        all.playTogether(set1, set2);
        all.start();

        all.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                ViewCompat.animate(v).alpha(0).setDuration(500).start();
            }
        });


    }


}
