package com.qpyy.libcommon.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.graphics.drawable.Animatable2Compat;
import android.util.AttributeSet;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.qpyy.libcommon.R;
import com.qpyy.libcommon.bean.FaceBean;

public class ExpressionImgView extends android.support.v7.widget.AppCompatImageView {

    private Context mContext;
    protected int[] arrayRandom = new int[]{R.drawable.random0, R.drawable.random1, R.drawable.random2, R.drawable.random3, R.drawable.random4, R.drawable.random5, R.drawable.random6, R.drawable.random7, R.drawable.random8, R.drawable.random9};

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            handler.removeMessages(0);
            setVisibility(GONE);
        }
    };


    public ExpressionImgView(Context context) {
        this(context, null);
    }

    public ExpressionImgView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ExpressionImgView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }


    public void addData(FaceBean faceBean) {
        setVisibility(VISIBLE);
        handler.removeMessages(0);
        if (faceBean.getType() == 1) {
            Glide.with(mContext).load(faceBean.getFace_spectial()).into(new SimpleTarget<Drawable>() {
                @Override
                public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                    if (resource instanceof GifDrawable) {
                        ExpressionImgView.this.setImageDrawable(resource);
                        ((GifDrawable) resource).start();
                        handler.sendEmptyMessageDelayed(0, 3000);
                    }
                }
            });
        } else {
            Glide.with(mContext).load(arrayRandom[faceBean.getNumber()]).into(new SimpleTarget<Drawable>() {
                @Override
                public void onResourceReady(@NonNull Drawable drawable, @Nullable Transition<? super Drawable> transition) {
                    if (drawable instanceof GifDrawable) {
                        ExpressionImgView.this.setImageDrawable(drawable);
                        ((GifDrawable) drawable).start();
                        ((GifDrawable) drawable).setLoopCount(1);
                        ((GifDrawable) drawable).registerAnimationCallback(new Animatable2Compat.AnimationCallback() {
                            @Override
                            public void onAnimationEnd(Drawable drawable) {
                                super.onAnimationEnd(drawable);
                                handler.sendEmptyMessageDelayed(0, 500);
                            }
                        });
                    }
                }
            });
        }
    }


    public void remove() {
        setVisibility(GONE);
    }

}
