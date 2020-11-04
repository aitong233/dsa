package com.spadea.xqipao.ui.me.activity;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.Constants;
import com.spadea.yuyin.util.pay.PayEvent;
import com.spadea.yuyin.util.pay.PaymentUtil;
import com.spadea.yuyin.util.utilcode.BarUtils;
import com.spadea.yuyin.util.utilcode.LogUtils;
import com.spadea.yuyin.util.utilcode.ToastUtils;
import com.spadea.xqipao.common.Constant;
import com.spadea.xqipao.common.aroute.ARouters;
import com.qpyy.libcommon.bean.RechargeInfoModel;
import com.spadea.xqipao.data.WxPayModel;
import com.spadea.xqipao.ui.base.view.BaseActivity;
import com.spadea.xqipao.ui.me.adapter.RechargeAdapter;
import com.spadea.xqipao.ui.me.contacter.BalanceContacter;
import com.spadea.xqipao.ui.me.presenter.BalancePresenter;
import com.spadea.xqipao.utils.StatisticsUtils;
import com.spadea.xqipao.widget.dialog.CustomRechargeAlertDialog;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tendcloud.tenddata.TCAgent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.math.BigDecimal;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 充值
 */
@Route(path = ARouters.ME_BALANCE, name = "充值")
public class BalanceActivity extends BaseActivity<BalancePresenter> implements BalanceContacter.View {


    @BindView(R.id.tv_balance)
    TextView tvBalance;
    @BindView(R.id.iv_weixin)
    ImageView ivWeixin;
    @BindView(R.id.rl_weixin_pay)
    RelativeLayout rlWeixinPay;
    @BindView(R.id.iv_zhifubao)
    ImageView ivZhifubao;
    @BindView(R.id.rl_zhifubao_pay)
    RelativeLayout rlZhifubaoPay;
    @BindView(R.id.tv_payment)
    TextView tvPayment;
    @BindView(R.id.rv_recharge)
    RecyclerView rvRecharge;

    @Autowired
    public boolean withFinish;

    private RechargeAdapter adapter = new RechargeAdapter();
    private String money = "0";
    private int type = 1;

    public BalanceActivity() {
        super(R.layout.activity_balance);
    }

    @Override
    protected void initData() {
        StatisticsUtils.addEvent(this, Constant.OpenInstall.RECHARGEPAGE);
        MvpPre.rechargeInfo();
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        BarUtils.setStatusBarAlpha(this, 0);
        BarUtils.setAndroidNativeLightStatusBar(this, false);
        rvRecharge.setLayoutManager(new GridLayoutManager(this, 3));
        rvRecharge.setAdapter(adapter);
        ivZhifubao.setImageLevel(1);
        adapter.setOnItemClickListener((adapter, view, position) -> {
            RechargeInfoModel rechargeInfoModel =   (RechargeInfoModel)adapter.getData().get(position);
            money = rechargeInfoModel.getMoney();
            tvPayment.setText("立即支付（" + money + "元)");
        });
    }

    @Override
    protected BalancePresenter bindPresenter() {
        return new BalancePresenter(this, this);
    }

    @Override
    public void showLoadings() {
        showLoading();
    }

    @Override
    public void disLoadings() {
        disLoading();
    }


    @OnClick({ R.id.tv_payment, R.id.rl_weixin_pay, R.id.rl_zhifubao_pay, R.id.iv_back, R.id.tv_record})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.tv_payment:
                if (money.equals("0")) {
                    ToastUtils.showShort("请选择充值金额");
                    return;
                }
                if (Double.valueOf(money) < 6) {
                    ToastUtils.showShort("最低充值6元以上");
                    return;
                }
                if (type == 1) {
                    StatisticsUtils.addEvent(this, Constant.OpenInstall.ALIRECHARGE);
                } else {
                    StatisticsUtils.addEvent(this, Constant.OpenInstall.WXRECHARGE);
                }
                StatisticsUtils.addEvent(this, Constant.OpenInstall.TOTALRECHARGE);
                MvpPre.userRecharge(money, type);
                break;
            case R.id.rl_weixin_pay:
                type = 2;
                ivWeixin.setImageLevel(1);
                ivZhifubao.setImageLevel(0);
                break;
            case R.id.rl_zhifubao_pay:
                type = 1;
                ivWeixin.setImageLevel(0);
                ivZhifubao.setImageLevel(1);
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_record:
                startActivity(new Intent(this, PaymentDetailsActivity.class));
                break;
        }
    }

    CustomRechargeAlertDialog customDialog;

//    void showCusTomDialog() {
//        if (customDialog == null) {
//            customDialog = new CustomRechargeAlertDialog(this, new CustomRechargeAlertDialog.CustomDialogListener() {
//                @Override
//                public void onConfirm() {
//                    String money = customDialog.editText.getText().toString().trim();
//                    if (!TextUtils.isEmpty(money)) {
//                        BalanceActivity.this.money = money;
//                        tvCustom.setText(money);
//                        tvPayment.setText("立即支付（" + money + "元)");
//                    }
//                }
//
//                @Override
//                public void onCancel() {
//                    customDialog.dismiss();
//                }
//            });
//
//        }
//        customDialog.show();
//    }

    @Override
    public void userRechargeSuccess(String orderId, int type) {
        if (type == 1) {
            MvpPre.aliPay(orderId, 1);
        } else {
            MvpPre.weixinPay(orderId, 1);
        }
    }

    @Override
    public void aliPayment(String orderNo) {
        PaymentUtil.payAlipay(this, orderNo);
    }

    @Override
    public void weixinPayment(WxPayModel wxPayModel) {
        IWXAPI wxapi = WXAPIFactory.createWXAPI(this, wxPayModel.getAppid());
        PaymentUtil.payWxPayment(wxapi, wxPayModel);
    }


    @Override
    public void setBalanceMoney(String money) {
        if (!TextUtils.isEmpty(money)) {
            tvBalance.setText(new BigDecimal(money).setScale(2, BigDecimal.ROUND_DOWN).toPlainString());
        } else {
            tvBalance.setText("0.00");
        }
    }

    @Override
    public void rechargeInfoSuccess(ArrayList<RechargeInfoModel> s) {
        adapter.setNewData(s);
    }


    @Override
    protected void onResume() {
        super.onResume();
        MvpPre.getBalance();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onDataSynEvent(PayEvent event) {
        if (event.getType() == Constants.PAYSUCESS) {
            try {
                if (type == 1) {
                    StatisticsUtils.addPaySuccessEvent(this, Constant.OpenInstall.ALIRECHARGETOTAL, money);
                } else {
                    StatisticsUtils.addPaySuccessEvent(this, Constant.OpenInstall.WXRECHARGETOTAL, money);
                }
                StatisticsUtils.addPaySuccessEvent(this, Constant.OpenInstall.RECHARGETOTAL, money);
            } catch (Exception e) {
                e.printStackTrace();
                LogUtils.e("上报错误", e.getMessage());
                TCAgent.onError(this, e);
            }
            if (withFinish) {
                finish();
            } else {
                MvpPre.getBalance();
            }
        }
    }
}
