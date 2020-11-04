package com.qpyy.libcommon.widget.animator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.blankj.utilcode.util.BarUtils;

public class ExplosionField extends View {
    private static final String TAG = "ExplosionField";
    private static final Canvas mCanvas = new Canvas();
    private ExplosionAnimator animator;

    public ExplosionField(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        animator.draw(canvas);
    }

    /**
     * 执行爆破破碎动画
     */
    public void explode(final View view, final AnimatorListenerAdapter listener) {
        Rect rect = new Rect();
        view.getGlobalVisibleRect(rect); //得到view相对于整个屏幕的坐标
        rect.offset(0, -BarUtils.getStatusBarHeight()); //去掉状态栏高度
        if (rect.width() == 0 || rect.height() == 0) {
            return;
        }
        animator = new ExplosionAnimator(this, createBitmapFromView(view), rect);

        // 接口回调
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                if (listener != null) listener.onAnimationStart(animation);
                // 延时添加到界面上
                attach2Activity((Activity) getContext());
                // 让被爆炸的View消失（爆炸的View是新创建的View，原View本身不会发生任何变化）
                view.animate().alpha(0f).setDuration(150).start();
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (listener != null) listener.onAnimationEnd(animation);
                // 从界面中移除
                removeFromActivity((Activity) getContext());
                // 让被爆炸的View显示（爆炸的View是新创建的View，原View本身不会发生任何变化）
                view.animate().alpha(1f).setDuration(150).start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                if (listener != null) listener.onAnimationCancel(animation);
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                if (listener != null) listener.onAnimationRepeat(animation);
            }
        });
        animator.start();
    }

    private Bitmap createBitmapFromView(View view) {
//         为什么屏蔽以下代码段？
//         如果ImageView直接得到位图，那么当它设置背景（backgroud)时，不会读取到背景颜色
//        if (view instanceof ImageView) {
//            Drawable drawable = ((ImageView)view).getDrawable();
//            if (drawable != null && drawable instanceof BitmapDrawable) {
//                return ((BitmapDrawable) drawable).getBitmap();
//            }
//        }
        //view.clearFocus(); //不同焦点状态显示的可能不同——（azz:不同就不同有什么关系？）

        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);

        if (bitmap != null) {
            synchronized (mCanvas) {
                mCanvas.setBitmap(bitmap);
                view.draw(mCanvas);
                // 清除引用
                mCanvas.setBitmap(null);
            }
        }
        return bitmap;
    }

    /**
     * 将创建的ExplosionField添加到Activity上
     */
    private void attach2Activity(Activity activity) {
        ViewGroup rootView = activity.findViewById(Window.ID_ANDROID_CONTENT);

        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        rootView.addView(this, lp);
    }

    /**
     * 将ExplosionField从Activity上移除
     */
    private void removeFromActivity(Activity activity) {
        ViewGroup rootView = activity.findViewById(Window.ID_ANDROID_CONTENT);
        rootView.removeView(this);
    }
}