package com.yutang.game.grabmarbles.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yutang.game.grabmarbles.R;
import com.yutang.game.grabmarbles.utils.SizeUtils;


public class StrokeTextView extends AppCompatTextView {

    private TextView borderText;///用于描边的TextView
    private float strokeWidth;
    private int strokeColor;
    private boolean showGradientColor = false;
    private boolean showStroke = false;

    public StrokeTextView(Context context) {
        super(context);
        borderText = new TextView(context);
        init();
    }

    public StrokeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        borderText = new TextView(context, attrs);
        init(context, attrs);
    }

    public StrokeTextView(Context context, AttributeSet attrs,
                          int defStyle) {
        super(context, attrs, defStyle);
        borderText = new TextView(context, attrs, defStyle);
        init(context, attrs);
    }

    public void init(Context context, AttributeSet attrs) {
        //获取自定义属性。
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.StrokeTextView);
        //获取字体大小,默认大小是16dp
        int defaultWidth = SizeUtils.INSTANCE.dp2px(context, 2f);
        strokeWidth = ta.getDimension(R.styleable.StrokeTextView_strokeWidth, defaultWidth);
        strokeColor = ta.getColor(R.styleable.StrokeTextView_strokeColor, getResources().getColor(R.color.ColorStroke));
        showGradientColor = ta.getBoolean(R.styleable.StrokeTextView_isGradient, false);
        showStroke = ta.getBoolean(R.styleable.StrokeTextView_showStroke, true);
        ta.recycle();
        init();
    }

    public void init() {
        if (showStroke) {
            TextPaint tp1 = borderText.getPaint();
            tp1.setStrokeWidth(strokeWidth);                                  //设置描边宽度
            tp1.setStyle(Paint.Style.STROKE);                             //对文字只描边
            borderText.setTextColor(strokeColor);  //设置描边颜色
            borderText.setGravity(getGravity());
        }
        if (showGradientColor) {
            LinearGradient linearGradient = new LinearGradient(0, 0, 0, getPaint().getTextSize(), Color.parseColor("#FFDF6D45"), Color.parseColor("#FFEBAE57"), Shader.TileMode.CLAMP);
            getPaint().setShader(linearGradient);
        }
    }

    @Override
    public void setLayoutParams(ViewGroup.LayoutParams params) {
        super.setLayoutParams(params);
        borderText.setLayoutParams(params);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        CharSequence tt = borderText.getText();

        //两个TextView上的文字必须一致
        if (tt == null || !tt.equals(this.getText())) {
            borderText.setText(getText());
            this.postInvalidate();
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        borderText.measure(widthMeasureSpec, heightMeasureSpec);
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        borderText.layout(left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        borderText.draw(canvas);
        super.onDraw(canvas);
    }
}