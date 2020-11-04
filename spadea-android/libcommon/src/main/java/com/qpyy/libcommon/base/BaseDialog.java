package com.qpyy.libcommon.base;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;

import com.qpyy.libcommon.R;

import butterknife.ButterKnife;

public abstract class BaseDialog extends Dialog {

    public BaseDialog(@NonNull Context context) {
        this(context, R.style.BaseDialogStyle);
    }

    public BaseDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initView();
        initData();
    }


    public abstract int getLayoutId();

    public abstract void initView();

    public abstract void initData();


}