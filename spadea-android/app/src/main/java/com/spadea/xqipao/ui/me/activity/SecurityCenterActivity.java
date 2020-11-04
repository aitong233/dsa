package com.spadea.xqipao.ui.me.activity;


import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.spadea.yuyin.R;
import com.spadea.xqipao.common.Constant;
import com.spadea.xqipao.common.aroute.ARouters;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class SecurityCenterActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.rel_total_bar)
    RelativeLayout relTotalBar;

    public SecurityCenterActivity() {
        super(R.layout.activity_security_center);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        relTotalBar.setBackgroundResource(R.color.white);
        tvTitle.setText("安全中心");
    }

    @Override
    protected IPresenter bindPresenter() {
        return null;
    }

    @Override
    public void showLoadings() {

    }


    @OnClick({R.id.iv_back, R.id.lin_logout_help, R.id.lin_logout_account})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.lin_logout_help:
                ARouter.getInstance().build(ARouters.H5).withString("url", Constant.URL.URL_LOGOUT_HELP).withString("title", "帮助").navigation();
                break;
            case R.id.lin_logout_account:
                startActivity(new Intent(this, SecurityCenterPromptedActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    public void disLoadings() {

    }
}