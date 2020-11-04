package com.qpyy.libcommon.widget.dialog;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.view.LayoutInflater;
import android.view.View;

import butterknife.ButterKnife;

public abstract class BaseBottomSheetDialog extends BottomSheetDialog {

    public Context mContext;
    private Object object;

    public BaseBottomSheetDialog(@NonNull Context context) {
        super(context);
        this.mContext = context;
        View dialogView = LayoutInflater.from(mContext).inflate(getLayout(), null, false);
        ButterKnife.bind(this, dialogView);
        setContentView(dialogView);
        ((View) dialogView.getParent()).setBackgroundColor(Color.parseColor("#00000000"));
        initView();
        initData();
    }

    public abstract int getLayout();

    public abstract void initView();

    public abstract void initData();

    public void setTag(Object object) {
        this.object = object;
    }

    public Object getTag() {
        return object;
    }
}
