package com.qpyy.libcommon.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;


public abstract class BaseMvpFragment<P extends IPresenter> extends BaseFragment implements IView<Activity> {
    protected P MvpPre;

    protected abstract P bindPresenter();

    @Override
    public FragmentActivity getSelfActivity() {
        return getActivity();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        MvpPre = bindPresenter();
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        if (MvpPre != null) {
            MvpPre.detachView();
        }
        super.onDestroyView();
    }

    @Override
    public void showLoadings() {
        showLoading("加载中...");
    }

    @Override
    public void showLoadings(String content) {
        showLoading(content);
    }

    @Override
    public void disLoadings() {
        disLoading();
    }
}
