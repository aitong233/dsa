package com.spadea.xqipao.utils.dialog;

import android.content.Context;
import android.support.annotation.NonNull;

import com.spadea.yuyin.R;

public class ProgressDialog extends BaseDialog {


    public ProgressDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public int getLayout() {
        return R.layout.dialog_my_progress;
    }

    @Override
    public  void initView() {

    }

    @Override
    public  void initData() {

    }
}
