package com.spadea.xqipao.ui.base.view;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.launcher.ARouter;
import com.qpyy.libcommon.widget.dialog.LoadingDialog;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.utils.dialog.LoadingAnim;
import com.tendcloud.tenddata.TCAgent;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseActivity<P extends IPresenter> extends AppCompatActivity implements IView<Activity> {
    protected P MvpPre;
    protected Unbinder mUnbinder;
    protected int layoutId;


    protected LoadingAnim loadingAnim;
    protected LoadingDialog mLoadingDialog;

    public BaseActivity(int layoutId) {
        this.layoutId = layoutId;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (layoutId != -1) {
            setContentView(layoutId);
        }
        MvpPre = bindPresenter();
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

    protected abstract P bindPresenter();

    @Override
    public Activity getSelfActivity() {
        return this;
    }

    @Override
    protected void onResume() {
        super.onResume();
        TCAgent.onPageStart(this, this.getClass().getSimpleName());
    }

    @Override
    protected void onPause() {
        super.onPause();
        TCAgent.onPageEnd(this, this.getClass().getSimpleName());
    }

    @Override
    protected void onDestroy() {
        /**
         * 在生命周期结束时，将presenter与view之间的联系断开，防止出现内存泄露
         */
        mUnbinder.unbind();
        if (MvpPre != null) {
            MvpPre.detachView();
        }
        super.onDestroy();
    }


    public void showLoading() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            if (isFinishing() || isDestroyed()) {
                return;
            }
        }
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

    public void showCommonLoading() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            if (isFinishing() || isDestroyed()) {
                return;
            }
        }
        if (mLoadingDialog == null) {
            mLoadingDialog = new LoadingDialog(this);
        }
        if (!mLoadingDialog.isShowing()) {
            mLoadingDialog.show();
        }
    }

    public void disCommonLoading() {
        try {
            if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
                mLoadingDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
