package com.spadea.xqipao.utils.view;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;


public class MarqueeTextView extends android.support.v7.widget.AppCompatTextView {

    public MarqueeTextView(Context context) {
        this(context, null);
    }

    public MarqueeTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        // 设置单行
        setSingleLine();
//        setMaxLines(1);
        //设置 Ellipsize，setMaxLines(1) 和 setEllipsize 冲突
        setEllipsize(TextUtils.TruncateAt.MARQUEE);
        //获取焦距
        setFocusable(true);
        //走马灯的重复次数，-1代表无限重复
        setMarqueeRepeatLimit(-1);
        //强制获得焦点
        setFocusableInTouchMode(true);
    }


    /**
     * 用于 EditText 存在时抢占焦点
     */
    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        if (focused) {
            super.onFocusChanged(focused, direction, previouslyFocusedRect);
        }
    }

    /**
     * Window与Window间焦点发生改变时的回调
     * 解决 Dialog 抢占焦点问题
     *
     * @param hasWindowFocus
     */
    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        if (hasWindowFocus) {
            super.onWindowFocusChanged(hasWindowFocus);
        }
    }

    @Override
    public boolean isFocused() {//必须重写，且返回值是true，表示始终获取焦点
        return true;
    }


}
