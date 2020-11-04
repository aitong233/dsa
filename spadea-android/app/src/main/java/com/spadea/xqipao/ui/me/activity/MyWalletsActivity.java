package com.spadea.xqipao.ui.me.activity;


import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.qpyy.libcommon.utils.SpUtils;
import com.spadea.yuyin.R;
import com.spadea.xqipao.ui.base.view.BaseActivity;
import com.spadea.xqipao.ui.me.contacter.MyWalletsContacter;
import com.spadea.xqipao.ui.me.presenter.MyWalletsPresenter;

import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的钱包
 */
@Route(path = ARouteConstants.ME_WALLETS, name = "我的钱包")
public class MyWalletsActivity extends BaseActivity<MyWalletsPresenter> implements MyWalletsContacter.View {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tv_my)
    TextView tvMy;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.rl_my_income)
    RelativeLayout rlMyIncome;
    @BindView(R.id.iv_transfer)
    ImageView ivTransfer;

    public MyWalletsActivity() {
        super(R.layout.activity_my_wallets);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        tvTitle.setText("我的钱包");
        if (SpUtils.getTransferStatus())
            ivTransfer.setVisibility(View.VISIBLE);
        else
            ivTransfer.setVisibility(View.GONE);

    }

    @Override
    protected MyWalletsPresenter bindPresenter() {
        return new MyWalletsPresenter(this, this);
    }

    @Override
    public void showLoadings() {

    }

    @Override
    public void disLoadings() {

    }


    @OnClick({R.id.rl_my_income, R.id.rl_capital_details, R.id.iv_back, R.id.iv_recharge, R.id.iv_transfer})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_my_income:
                startActivity(new Intent(this, ProfitActivity.class));
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_capital_details:
                startActivity(new Intent(this, PaymentDetailsActivity.class));
                break;
            case R.id.iv_recharge:
                startActivity(new Intent(this, BalanceActivity.class));
                break;
            case R.id.iv_transfer:
                ARouter.getInstance().build(ARouteConstants.ME_TRANSFERUSER).navigation();
                break;
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        MvpPre.getBalance();
    }

    @Override
    public void setBalanceMoney(String money) {
        if (!TextUtils.isEmpty(money)) {
            tvNum.setText(new BigDecimal(money).setScale(2, BigDecimal.ROUND_DOWN).toPlainString());
        } else {
            tvNum.setText("0.00");
        }
    }


}
