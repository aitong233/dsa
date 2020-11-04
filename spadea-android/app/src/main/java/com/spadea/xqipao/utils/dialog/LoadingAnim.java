package com.spadea.xqipao.utils.dialog;

import android.content.Context;
import android.support.annotation.NonNull;

import com.spadea.yuyin.R;

public class LoadingAnim extends BaseDialog {


    public LoadingAnim(@NonNull Context context) {
        super(context);
    }

    @Override
    public int getLayout() {
        return R.layout.dialog_loadings;
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData() {

    }
}