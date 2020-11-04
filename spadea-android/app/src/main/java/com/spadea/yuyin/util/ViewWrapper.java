package com.spadea.yuyin.util;

import android.view.View;

public  class ViewWrapper {
    private View mTarget;

    public ViewWrapper(View view) {
        mTarget = view;
    }

    public void setTrueWidth(int width) {
        mTarget.getLayoutParams().width = width;
        mTarget.requestLayout();//必须调用，否则宽度改变但UI没有刷新
    }


    public void setTrueHeight(int height) {
        mTarget.getLayoutParams().height = height;
        mTarget.requestLayout();//必须调用，否则宽度改变但UI没有刷新
    }

    public int getTrueHeight() {
        return mTarget.getLayoutParams().height;
    }


    public int getTrueWidth() {
        return mTarget.getLayoutParams().width;
    }
}