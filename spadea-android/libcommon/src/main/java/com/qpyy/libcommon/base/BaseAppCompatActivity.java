package com.qpyy.libcommon.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.BarUtils;
import com.qpyy.libcommon.widget.dialog.LoadingDialog;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.libcommon.base
 * 创建人 王欧
 * 创建时间 2020/6/24 2:55 PM
 * 描述 describe
 */
public abstract class BaseAppCompatActivity extends AppCompatActivity {

    private Unbinder mBinder;

    private LoadingDialog mLoadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mBinder = ButterKnife.bind(this);
        ARouter.getInstance().inject(this);
        BarUtils.setStatusBarLightMode(this, isLightMode());
        BarUtils.transparentStatusBar(this);
        initView();
        initData();
    }

    public boolean isLightMode() {
        return true;
    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract int getLayoutId();

    @Override
    protected void onDestroy() {
        mBinder.unbind();
        super.onDestroy();
    }

    public void showLoading(String content) {
        if (mLoadingDialog == null) {
            mLoadingDialog = new LoadingDialog(this);
        }
        mLoadingDialog.setContent(content);
        if (!mLoadingDialog.isShowing()) {
            mLoadingDialog.show();
        }
    }

    public void disLoading() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }
    }
}
