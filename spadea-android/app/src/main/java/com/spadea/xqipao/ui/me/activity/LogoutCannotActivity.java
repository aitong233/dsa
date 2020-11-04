package com.spadea.xqipao.ui.me.activity;

import android.graphics.Paint;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.spadea.yuyin.R;
import com.spadea.xqipao.common.aroute.ARouters;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = ARouters.LOGOUT_CANNOT, name = "账号无法注销")
public class LogoutCannotActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.rel_total_bar)
    RelativeLayout relTotalBar;
    @BindView(R.id.tv_lc_ts_content1)
    TextView tvLcTsContent1;
    @BindView(R.id.tv_lc_sm_title1)
    TextView tvLcSmTitle1;
    @BindView(R.id.tv_lc_reason)
    TextView tvLcReason;

    @Autowired
    public String reason;

    public LogoutCannotActivity() {
        super(R.layout.activity_logout_cannot);
    }


    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        relTotalBar.setBackgroundResource(R.color.white);
        tvTitle.setText("注销账号");
        Paint paint1 = tvLcTsContent1.getPaint();
        paint1.setFakeBoldText(true);
        Paint paint2 = tvLcSmTitle1.getPaint();
        paint2.setFakeBoldText(true);
        tvLcReason.setText(reason);
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

    @OnClick({R.id.iv_back})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            default:
                break;
        }
    }
}