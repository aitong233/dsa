package com.spadea.xqipao.ui.me.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.spadea.yuyin.R;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class SecurityCenterPromptedActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.rel_total_bar)
    RelativeLayout relTotalBar;
    @BindView(R.id.tv_title1)
    TextView tvTitle1;
    @BindView(R.id.tv_title2)
    TextView tvTitle2;

    public SecurityCenterPromptedActivity() {
        super(R.layout.activity_security_center_prompted);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        relTotalBar.setBackgroundResource(R.color.white);
        tvTitle.setText("安全中心");
        Paint paint1 = tvTitle1.getPaint();
        paint1.setFakeBoldText(true);
        Paint paint2 = tvTitle2.getPaint();
        paint2.setFakeBoldText(true);
    }

    @Override
    protected IPresenter bindPresenter() {
        return null;
    }

    @Override
    public void showLoadings() {

    }

    @Override
    public void disLoadings() {

    }

    @OnClick({R.id.iv_back,R.id.tv_logout_confirm})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_logout_confirm:
                startActivity(new Intent(this, LogoutAccountActivity.class));
                finish();
                break;
            default:
                break;
        }
    }
}
