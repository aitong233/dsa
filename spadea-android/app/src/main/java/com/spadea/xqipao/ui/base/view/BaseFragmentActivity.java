package com.spadea.xqipao.ui.base.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;


import com.spadea.xqipao.ui.base.presenter.IPresenter;


public abstract class BaseFragmentActivity<P extends IPresenter> extends FragmentActivity implements IView<FragmentActivity> {
    protected P MvpPre;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MvpPre = bindPresenter();
    }

    protected abstract P bindPresenter();

    @Override
    public FragmentActivity getSelfActivity() {
        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        /**
         * 在生命周期结束时，将presenter与view之间的联系断开，防止出现内存泄露
         */
        if (MvpPre != null) {
            MvpPre.detachView();
        }
    }
}
