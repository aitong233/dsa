package com.spadea.xqipao.ui.base.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.launcher.ARouter;
import com.spadea.xqipao.utils.dialog.LoadingAnim;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseAppCompatActivity extends AppCompatActivity {
    protected Unbinder mUnbinder;
    protected int layoutId;


    protected LoadingAnim loadingAnim;

    public BaseAppCompatActivity(int layoutId) {
        this.layoutId = layoutId;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId);
        mUnbinder = ButterKnife.bind(this);
        ARouter.getInstance().inject(this);
        initView();
        initData();
        setListener();
    }

    protected void setListener() {

    }


    protected abstract void initData();

    protected abstract void initView();

    @Override
    protected void onDestroy() {
        mUnbinder.unbind();
        super.onDestroy();
    }


    public void showLoading() {
        if (loadingAnim == null) {
            loadingAnim = new LoadingAnim(this);
        }
        loadingAnim.show();
    }

    public void disLoading() {
        if (loadingAnim != null) {
            loadingAnim.cancel();
        }
    }

}
