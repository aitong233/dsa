package com.spadea.xqipao.utils.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Chen on 2017/6/4.
 * 水波纹进度条
 */

public class PitCountDownView extends View {

    Paint bgPaint;

    TextPaint textPaint;


    public PitCountDownView(Context context) {
        this(context,null);
    }

    public PitCountDownView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }
}
