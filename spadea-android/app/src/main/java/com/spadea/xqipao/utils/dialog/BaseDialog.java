package com.spadea.xqipao.utils.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;

import com.spadea.yuyin.R;

import butterknife.ButterKnife;

public abstract class BaseDialog extends Dialog {
    public Context mContext;
    public View dialogView;


    public BaseDialog(@NonNull Context context) {
        this(context,0);
    }

    public BaseDialog(@NonNull Context context, int themeResId) {
        super(context, R.style.defaultDialogStyle );//通过第二个参数指定其Style
        this.mContext = context;
        dialogView = LayoutInflater.from(mContext).inflate(getLayout(), null, false);
        ButterKnife.bind(this, dialogView);
        setContentView(dialogView);
//        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        ((View) dialogView.getParent()).setBackgroundColor(Color.parseColor("#00000000"));
        initView();
        initData();
    }


    public abstract int getLayout();

    public abstract void initView();

    public  abstract void initData();



}
