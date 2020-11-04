package com.spadea.xqipao.ui.me.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.flyco.tablayout.listener.OnTabSelectListener;
import com.qpyy.libcommon.utils.SpUtils;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.utilcode.BarUtils;
import com.spadea.yuyin.util.utilcode.ToastUtils;
import com.spadea.xqipao.data.ProfitModel;
import com.spadea.xqipao.ui.base.view.BaseActivity;
import com.spadea.xqipao.ui.me.contacter.ProfitContacter;
import com.spadea.xqipao.ui.me.presenter.ProfitPresenter;
import com.spadea.xqipao.utils.StatusBarUtil;
import com.spadea.xqipao.utils.dialog.ExchangeDialog;
import com.spadea.xqipao.utils.dialog.ExchangePasswordDialog;
import com.spadea.xqipao.widget.SegmentTabLayout;

import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的收益
 */
public class ProfitActivity extends BaseActivity<ProfitPresenter> implements ProfitContacter.View {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_add)
    ImageView ivAdd;
    @BindView(R.id.view_line)
    View viewLine;


    @BindView(R.id.rl_capital_details)
    RelativeLayout rlCapitalDetails;

    @BindView(R.id.tv_assets)
    TextView tvAssets;
    @BindView(R.id.tv_total)
    TextView tvTotal;
    @BindView(R.id.tv_day)
    TextView tvDay;
    @BindView(R.id.tv_week)
    TextView tvWeek;
    @BindView(R.id.tv_month)
    TextView tvMonth;
    @BindView(R.id.tv_last_month)
    TextView tvLastMonth;


    private boolean b = true;
    private int index = 0;
    private ExchangeDialog exchangeDialog;
    private ExchangePasswordDialog exchangePasswordDialog;

    public ProfitActivity() {
        super(R.layout.activity_profit);
    }

    @Override
    protected void initData() {
        MvpPre.getEarnings();
        MvpPre.getUserProfit();
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (tab == null) return;
        switch (index) {
            case 0:
                MvpPre.getUserProfit();
                break;

            case 1:
                MvpPre.getRoomProfit();
                break;
        }
    }

    @BindView(R.id.tab)
    SegmentTabLayout tab;
    @BindView(R.id.bg)
    View bg;

    @Override
    protected void initView() {
        BarUtils.setStatusBarAlpha(this, 0);
        BarUtils.setAndroidNativeLightStatusBar(this, true);
        StatusBarUtil.initTransP(bg, 0);
        tvTitle.setText("我的收益");
        String[] titles;
        if (SpUtils.getRoomType() > 0) titles = new String[]{"我的收益", "房间收益"};
        else titles = new String[]{"我的收益"};
        tab.setTabData(titles);
        tab.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                switch (position) {
                    case 0:
                        index = 0;
                        MvpPre.getUserProfit();
                        break;

                    case 1:
                        index = 1;
                        MvpPre.getRoomProfit();
                        break;
                }
            }

            @Override
            public void onTabReselect(int position) {

            }
        });


    }

    @Override
    protected ProfitPresenter bindPresenter() {
        return new ProfitPresenter(this, this);
    }

    @Override
    public void showLoadings() {

    }

    @Override
    public void disLoadings() {

    }

    @OnClick({R.id.iv_back, R.id.iv_withdrawal, R.id.iv_exchange, R.id.rl_capital_details, R.id.rl_add_bank})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            //提现
            case R.id.iv_withdrawal:
                if (index == 0) {
                    startActivity(new Intent(this, WithdrawalActivity.class));
                } else {
                    b = false;
                    showExchangDialog();
                }
                break;
            //佣金兑换
            case R.id.iv_exchange:
                b = true;
                showExchangDialog();
                break;
            case R.id.rl_capital_details:
                startActivity(new Intent(this, PaymentDetailsActivity.class));
                break;

            case R.id.rl_add_bank:
                startActivity(new Intent(this, AddBankActivity.class));
                break;
        }
    }

    private void showExchangDialog() {
//        if (MyApplication.getInstance().getUser().getSecond_password() == 0) {
//            new AlertDialog.Builder(this).setTitle("提示").setMessage("请先设置二级密码").setPositiveButton("去设置", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    if (TextUtils.isEmpty(MyApplication.getInstance().getUser().getMobile())) {
//                        ToastUtils.showShort("请先绑定手机号");
//                        return;
//                    }
//                    startActivity(new Intent(ProfitActivity.this, SecondLevelPasswordActivity.class));
//                }
//            }).setNegativeButton("取消", null).create().show();
//        } else {
        if (exchangeDialog == null) {
            exchangeDialog = new ExchangeDialog(this);
            exchangeDialog.setOnExchangeListener(new ExchangeDialog.OnExchangeListener() {
                @Override
                public void onInputNum(String num) {
//                        if (MyApplication.getInstance().getUser().getSecond_password() == 0) {
                    if (index == 0) {
                        ToastUtils.showShort("兑换金币");
                        MvpPre.convertEarnings(num, "");
                    } else {
                        if (b) {
                            MvpPre.exchangeRoomEarnings(num, "");
                        } else {
                            MvpPre.applyRoomProfit("", num);
                        }
                    }
//                        } else {
//                            showExchangePasswordDialog(num);
//                        }
                }
            });
        }
        exchangeDialog.setBalance(tvTotal.getText().toString(), b ? "收益余额" : "佣金余额");
        exchangeDialog.show();
        //}
    }

    private void showExchangePasswordDialog(String num) {
        exchangePasswordDialog = new ExchangePasswordDialog(ProfitActivity.this);
        exchangePasswordDialog.setOnExchangePasswordListener(new ExchangePasswordDialog.OnExchangePasswordListener() {
            @Override
            public void onPasswword(String password) {
                if (exchangeDialog != null) {
                    exchangeDialog.dismiss();
                }
                if (index == 0) {
                    MvpPre.convertEarnings(num, password);
                } else {
                    if (b) {
                        MvpPre.exchangeRoomEarnings(num, password);
                    } else {
                        MvpPre.applyRoomProfit(password, num);
                    }
                }
            }
        });
        exchangePasswordDialog.show();
    }


    @Override
    public void setEarnings(String earnings) {
        tvAssets.setText("当前财产：" + earnings);
    }

    @Override
    public void convertEarningsSuccess() {
        if (exchangeDialog != null) {
            exchangeDialog.dismiss();
        }
        if (exchangePasswordDialog != null) {
            exchangePasswordDialog.dismiss();
        }
        ToastUtils.showShort("兑换成功");
        MvpPre.getEarnings();
        if (index == 0) {
            MvpPre.getUserProfit();
        } else {
            MvpPre.getRoomProfit();
        }
    }

    @Override
    public void userProfit(ProfitModel profitModel) {
        tvTotal.setText(getFM(profitModel.getEarning()));
        tvDay.setText(getFM(profitModel.getToday()));
        tvMonth.setText(getFM(profitModel.getMonth()));
        tvWeek.setText(getFM(profitModel.getWeek()));
        tvLastMonth.setText(getFM(profitModel.getLast_month()));
    }


    String getFM(String text) {
        return new BigDecimal(text).setScale(2, BigDecimal.ROUND_DOWN).toPlainString();
    }

    @Override
    public void roomProfit(ProfitModel profitModel) {
        tvTotal.setText(getFM(profitModel.getRoom_earning()));
        tvDay.setText(getFM(profitModel.getToday()));
        tvMonth.setText(getFM(profitModel.getMonth()));
        tvWeek.setText(getFM(profitModel.getWeek()));
        tvLastMonth.setText(getFM(profitModel.getLast_month()));
    }

    @Override
    public void applyRoomProfitSuccess() {
        ToastUtils.showShort("申请佣金成功");
        MvpPre.getEarnings();
        if (index == 0) {
            MvpPre.getUserProfit();
        } else {
            MvpPre.getRoomProfit();
        }
    }


}
