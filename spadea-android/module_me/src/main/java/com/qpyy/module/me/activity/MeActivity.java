package com.qpyy.module.me.activity;

import android.widget.FrameLayout;

import com.blankj.utilcode.util.FragmentUtils;
import com.qpyy.libcommon.base.BaseAppCompatActivity;
import com.qpyy.libcommon.http.RetrofitClient;
import com.qpyy.libcommon.utils.SpUtils;
import com.qpyy.module.me.R;
import com.qpyy.module.me.R2;
import com.qpyy.module.me.fragment.MeFragment;

import butterknife.BindView;

public class MeActivity extends BaseAppCompatActivity {

    @BindView(R2.id.frame_layout)
    FrameLayout frameLayout;

    @Override
    protected void initData() {
//        RetrofitClient.getInstance().login();
        SpUtils.putToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiI2NDI3NDEiLCJpYXQiOjE1OTM3NTI5ODksImV4cCI6MTU5NjM0NDk4OX0.ggGKye3yy7PIqlK4XpX2B2wZUGKOAw5oRKZ5IcmyFxY");

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.me_activity_me;
    }


}
