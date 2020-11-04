package com.qpyy.libcommon.base;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.alibaba.android.arouter.launcher.ARouter;
import com.qpyy.libcommon.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.libcommon.base
 * 创建人 王欧
 * 创建时间 2020/7/14 4:01 PM
 * 描述 describe
 */
public abstract class BaseMvpDialogFragment<P extends IPresenter> extends DialogFragment implements IView<Activity> {
    protected Unbinder mUnBinder;

    protected P MvpPre;

    protected abstract P bindPresenter();

    @Override
    public Activity getSelfActivity() {
        return getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ARouter.getInstance().inject(this);
        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MvpPre = bindPresenter();
        mUnBinder = ButterKnife.bind(this, view);
        if (getArguments() != null) {
            initArgs(getArguments());
        }
        initView();
        initData();
    }

    public void initArgs(Bundle arguments) {

    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract int getLayoutId();

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setWindowAnimations(R.style.CommonShowDialogBottom);
        initDialogStyle(window);
    }

    protected void initDialogStyle(Window window) {

    }

    @Override
    public void showLoadings() {
        if (!isAdded() || getActivity() == null) {
            return;
        }
        if (getActivity() instanceof BaseAppCompatActivity) {
            ((BaseAppCompatActivity) getActivity()).showLoading("加载中...");
        }
    }

    @Override
    public void showLoadings(String content) {
        if (!isAdded() || getActivity() == null) {
            return;
        }
        if (getActivity() instanceof BaseAppCompatActivity) {
            ((BaseAppCompatActivity) getActivity()).showLoading(content);
        }
    }

    @Override
    public void disLoadings() {
        if (getActivity() instanceof BaseAppCompatActivity) {
            ((BaseAppCompatActivity) getActivity()).disLoading();
        }
    }

    @Override
    public void onDestroyView() {
        mUnBinder.unbind();
        if (MvpPre != null) {
            MvpPre.detachView();
        }
        super.onDestroyView();
    }
}
