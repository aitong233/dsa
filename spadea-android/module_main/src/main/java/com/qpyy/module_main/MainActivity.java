package com.qpyy.module_main;

import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.qpyy.libcommon.base.BaseAppCompatActivity;
import com.qpyy.libcommon.constant.ARouteConstants;

import butterknife.OnClick;

public class MainActivity extends BaseAppCompatActivity {


    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.main_activity_main;
    }

    @OnClick(R2.id.btn_info)
    public void onClick(View view) {
        ARouter.getInstance().build(ARouteConstants.IMPROVE_INFO).navigation();
    }
}
