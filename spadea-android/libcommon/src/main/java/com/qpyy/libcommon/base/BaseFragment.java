package com.qpyy.libcommon.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseFragment extends Fragment {
    protected Unbinder mUnBinder;

    protected boolean isViewCreated =false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        isViewCreated =true;
        super.onViewCreated(view, savedInstanceState);
        mUnBinder = ButterKnife.bind(this, view);
        if (getArguments() != null) {
            initArgs(getArguments());
        }
        initView();
        initData();
        initListener();
    }

    public void initArgs(Bundle arguments) {

    }

    @Override
    public void onDestroyView() {
        mUnBinder.unbind();
        super.onDestroyView();
    }

    protected void initListener() {

    }


    protected abstract void initData();

    protected abstract void initView();

    protected abstract int getLayoutId();


    public void showLoading(String content) {
        if (!isAdded() || getActivity() == null) {
            return;
        }
        if (getActivity() instanceof BaseAppCompatActivity) {
            ((BaseAppCompatActivity) getActivity()).showLoading(content);
        }
    }

    public void disLoading() {
        if (getActivity() instanceof BaseAppCompatActivity) {
            ((BaseAppCompatActivity) getActivity()).disLoading();
        }
    }
}
