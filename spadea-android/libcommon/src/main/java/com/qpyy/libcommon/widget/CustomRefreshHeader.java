package com.qpyy.libcommon.widget;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.orhanobut.logger.Logger;
import com.qpyy.libcommon.R;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.module.index.widget
 * 创建人 王欧
 * 创建时间 2020/6/30 3:55 PM
 * 描述 describe
 */
public class CustomRefreshHeader extends ConstraintLayout implements RefreshHeader {
    ImageView mImageView;
    AnimationDrawable mAnimationDrawable;

    public CustomRefreshHeader(Context context) {
        this(context, null);
    }

    public CustomRefreshHeader(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.index_header_custom_refresh, this);
        mImageView = findViewById(R.id.image);
        mAnimationDrawable = (AnimationDrawable) mImageView.getBackground();
    }

    @NonNull
    @Override
    public View getView() {
        return this;
    }

    @NonNull
    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Translate;
    }

    @Override
    public void setPrimaryColors(int... colors) {

    }

    @Override
    public void onInitialized(@NonNull RefreshKernel kernel, int height, int maxDragHeight) {
        Logger.e("onInitialized");
    }

    @Override
    public void onMoving(boolean isDragging, float percent, int offset, int height, int maxDragHeight) {

    }

    @Override
    public void onReleased(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {
        Logger.e("onReleased");
    }

    @Override
    public void onStartAnimator(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {
        Logger.e("onStartAnimator");
        //判断是否在运行
        if (!mAnimationDrawable.isRunning()) {
            //开启帧动画
            mAnimationDrawable.start();
        }
    }

    @Override
    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean success) {
        Logger.e("onFinish");
        if (mAnimationDrawable.isRunning()) {
            //开启帧动画
            mAnimationDrawable.stop();
        }
        return 0;
    }

    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {

    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }

    @Override
    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {

    }
}
