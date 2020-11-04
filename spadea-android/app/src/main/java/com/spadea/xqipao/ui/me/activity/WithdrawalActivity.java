package com.spadea.xqipao.ui.me.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.spadea.yuyin.MyApplication;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.utilcode.ToastUtils;
import com.spadea.xqipao.data.UserBankModel;
import com.spadea.xqipao.ui.base.view.BaseActivity;
import com.spadea.xqipao.ui.me.contacter.WithdrawalContacter;
import com.spadea.xqipao.ui.me.presenter.WithdrawalPresenter;
import com.spadea.xqipao.utils.EditTextUtil;

import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 提现
 */
public class WithdrawalActivity extends BaseActivity<WithdrawalPresenter> implements WithdrawalContacter.View {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.rl_add_bank)
    View rlAddBank;
    @BindView(R.id.tv_bank_name)
    TextView tvBankName;
    @BindView(R.id.tv_bank_num)
    TextView tvBankNum;
    @BindView(R.id.ed_num)
    EditText edNum;
    @BindView(R.id.tv_balance)
    TextView tvBalance;
    @BindView(R.id.tv_commit)
    TextView tvCommit;
    @BindView(R.id.rl_bank)
    RelativeLayout rlBank;

    private UserBankModel mBankInfo;
    private String money = "0.00";


    public WithdrawalActivity() {
        super(R.layout.activity_withdrawal);
    }

    @Override
    protected void initData() {
        EditTextUtil.setInputDecimals(edNum, 2);
    }

    @Override
    protected void initView() {
        tvTitle.setText("金币提现");
        this.money = MyApplication.getInstance().getUser().getWithdraw_earnings();
        tvBalance.setText(new BigDecimal(money).setScale(2, BigDecimal.ROUND_DOWN).toPlainString());
    }

    @Override
    protected WithdrawalPresenter bindPresenter() {
        return new WithdrawalPresenter(this, this);
    }

    @Override
    public void showLoadings() {

    }

    @Override
    public void disLoadings() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        MvpPre.getUserBank();
    }

    @OnClick({R.id.iv_back, R.id.tv_right, R.id.tv_commit, R.id.tv_all, R.id.rl_add_bank})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_right:
                if (TextUtils.isEmpty(MyApplication.getInstance().getUser().getMobile())) {
                    ToastUtils.showShort("请先绑定手机号码");
                    return;
                }
                Intent intent = new Intent(this, AddBankActivity.class);
                //intent.putExtra("BANKINFO", mBankInfo);
                startActivity(intent);
                break;
            case R.id.tv_all:
                String s = tvBalance.getText().toString();
                if (!TextUtils.isEmpty(s)) {
                    edNum.setText(s);
                }
                break;
            case R.id.rl_add_bank:
                if (TextUtils.isEmpty(MyApplication.getInstance().getUser().getMobile())) {
                    ToastUtils.showShort("请先绑定手机号码");
                    return;
                }
                startActivity(new Intent(this, AddBankActivity.class));
                break;
            case R.id.tv_commit:
                String num = edNum.getText().toString();
                if (mBankInfo == null) {
                    ToastUtils.showShort("请先绑定支付宝");
                    return;
                }
                if (TextUtils.isEmpty(num)) {
                    ToastUtils.showShort("请输入提现金额");
                    return;
                }
                MvpPre.userWithdraw("", num);
                break;
        }
    }

    @Override
    public void setUserMoney(String money) {
        if (!TextUtils.isEmpty(money)) {
            this.money = MyApplication.getInstance().getUser().getWithdraw_earnings();
            tvBalance.setText(new BigDecimal(money).setScale(2, BigDecimal.ROUND_DOWN).toPlainString());
        } else {
            tvBalance.setText("0.00");
        }
        tvBalance.setTag(money);
    }

    @BindView(R.id.zfbid)
    TextView zfbid;
    @BindView(R.id.zfbname)
    TextView zfbname;

    @Override
    public void setUserBank(UserBankModel bankInfo) {
        mBankInfo = bankInfo;
        if (bankInfo != null) {
            zfbid.setText("支付宝账号:" + bankInfo.getAlipay_account());
            zfbname.setText("支付宝姓名:" + bankInfo.getAlipay_realname());
        } else {

        }
    }

    @Override
    public void userWithdrawSuccess() {
        edNum.setText("");
        ToastUtils.showShort("提现申请成功");
        MvpPre.getUserBank();
    }


}
