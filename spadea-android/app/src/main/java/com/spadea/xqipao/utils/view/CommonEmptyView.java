package com.spadea.xqipao.utils.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.spadea.yuyin.R;

public class CommonEmptyView extends FrameLayout {
    public CommonEmptyView(@NonNull Context context) {
        super(context);
        initViews();
    }

    public CommonEmptyView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initViews();
    }

    public CommonEmptyView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews();
    }

    private TextView tv_empty;

    private void initViews() {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_empty, this);
        tv_empty = findViewById(R.id.tv_empty_text);
    }

    public void setEmptyText(String text) {
        if (null != tv_empty) {
            tv_empty.setText(text);
        }
    }


}
