package com.spadea.xqipao.ui.base.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spadea.xqipao.ui.base.presenter.IPresenter;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseDialogFragment<P extends IPresenter> extends DialogFragment implements IView<FragmentActivity> {
    private static final String TAG = "BaseDialogFragment";

    protected P MvpPre;
    protected View rootView;
    protected Context mContext;
    protected Unbinder mUnbinder;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MvpPre = bindPresenter();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        rootView = super.onCreateView(inflater, container, savedInstanceState);
        if (rootView == null) {
            rootView = LayoutInflater.from(getActivity()).inflate(getLayoutId(), container, false);
            mContext = getContext();
            mUnbinder = ButterKnife.bind(this, rootView);
            initView(rootView);
            initData();
            initListener();
        } else {
            mUnbinder = ButterKnife.bind(this, rootView);
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null) {
                parent.removeView(rootView);
            }
        }
        return rootView;
    }


    protected void initListener() {

    }


    protected abstract void initData();

    protected abstract void initView(View rootView);

    protected abstract int getLayoutId();

    protected abstract P bindPresenter();

    @Override
    public FragmentActivity getSelfActivity() {
        return getActivity();
    }

    @Override
    public void showLoadings() {

    }

    @Override
    public void disLoadings() {

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        /**
         * 在生命周期结束时，将presenter与view之间的联系断开，防止出现内存泄露
         */
        mUnbinder.unbind();
        if (MvpPre != null) {
            MvpPre.detachView();
        }
    }


    public void show(FragmentManager fragmentManager) {
        show(fragmentManager, TAG);
    }

}
