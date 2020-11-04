package com.qpyy.libcommon.base;

import android.app.Activity;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.libcommon.base
 * 创建人 王欧
 * 创建时间 2020/6/24 4:05 PM
 * 描述 describe
 */
public abstract class BaseMvpActivity<P extends IPresenter> extends BaseAppCompatActivity implements IView<Activity> {

    protected P MvpPre;

    protected abstract P bindPresenter();

    @Override
    protected void initView() {
        MvpPre = bindPresenter();
    }

    @Override
    public void showLoadings() {
        showLoading("加载中");
    }

    @Override
    public void showLoadings(String content) {
        showLoading(content);
    }

    @Override
    public void disLoadings() {
        disLoading();
    }

    @Override
    protected void onDestroy() {
        if (MvpPre != null) {
            MvpPre.detachView();
        }
        super.onDestroy();
    }

    @Override
    public Activity getSelfActivity() {
        return this;
    }
}
