package com.spadea.xqipao.ui.me.activity;

import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.spadea.yuyin.MyApplication;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.utilcode.ToastUtils;
import com.spadea.xqipao.data.UserBankModel;
import com.spadea.xqipao.ui.base.view.BaseActivity;
import com.spadea.xqipao.ui.me.contacter.AddBankContacter;
import com.spadea.xqipao.ui.me.presenter.AddBankPresenter;
import com.spadea.xqipao.utils.BankUtil;
import com.spadea.xqipao.utils.StatusBarUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 添加银行
 */
public class AddBankActivity extends BaseActivity<AddBankPresenter> implements AddBankContacter.View {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.ed_name)
    EditText edName;
    @BindView(R.id.ed_phone)
    EditText edPhone;
    @BindView(R.id.ed_card)
    EditText edCard;
    @BindView(R.id.ed_bank_number)
    EditText edBankNumber;
    @BindView(R.id.ed_bank_name)
    EditText edBankName;
    @BindView(R.id.tv_add_bank)
    TextView tvAddBank;
    @BindView(R.id.ed_city_bank_name)
    EditText edCityBankName;
    @BindView(R.id.tv_code)
    EditText tvCode;
    @BindView(R.id.tv_smsCode)
    Button tvSmsCode;
    @BindView(R.id.bg)
    View bg;


    private BankUtil bankUtil;
    private UserBankModel.BankInfo bankInfo = null;

    public AddBankActivity() {
        super(R.layout.activity_bank);
    }

    @Override
    protected void initData() {
        bankInfo = (UserBankModel.BankInfo) getIntent().getSerializableExtra("BANKINFO");
        if (!TextUtils.isEmpty(MyApplication.getInstance().getUser().getMobile())) {
            edPhone.setHint(MyApplication.getInstance().getUser().getMobile());
        }
        if (bankInfo != null) {
            tvRight.setVisibility(View.VISIBLE);
            tvRight.setText("编辑");
            edBankName.setText(bankInfo.getBank_name());
            edCityBankName.setText(bankInfo.getBank_zhi());
            tvTitle.setText("更换支付宝");
            tvAddBank.setText("确认更换");
            setEnnableEdit(false);
        } else {
            tvRight.setVisibility(View.GONE);
            tvTitle.setText("绑定支付宝");
            tvAddBank.setText("确认绑定");
            setEnnableEdit(true);
        }
    }

    @Override
    protected void initView() {
        StatusBarUtil.initTransP(bg,0);
    }

    @Override
    protected AddBankPresenter bindPresenter() {
        return new AddBankPresenter(this, getBaseContext());
    }

    @OnClick({R.id.iv_back, R.id.tv_add_bank, R.id.tv_smsCode, R.id.tv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_add_bank:
                String name = edName.getText().toString().trim();
                String phone = MyApplication.getInstance().getUser().getMobile();
                //String idCard = edCard.getText().toString().trim();
                String bankId = edBankNumber.getText().toString().trim();
                //String branchBank = edBankName.getText().toString().trim();
                //String cityBankName = edCityBankName.getText().toString().trim();
                String code = tvCode.getText().toString().trim();
                if (TextUtils.isEmpty(phone)) {
                    ToastUtils.showShort("请先绑定手机号");
                    return;
                }
                if (TextUtils.isEmpty(name)) {
                    ToastUtils.showShort("请输入姓名");
                    return;
                }
//                if (TextUtils.isEmpty(idCard)) {
//                    ToastUtils.showShort("请输入本人身份证号");
//                    return;
//                }
                if (TextUtils.isEmpty(bankId)) {
                    ToastUtils.showShort("请输入支付宝账号");
                    return;
                }
//                if (TextUtils.isEmpty(branchBank)) {
//                    ToastUtils.showShort("请输入开户行");
//                    return;
//                }
//                if (TextUtils.isEmpty(cityBankName)) {
//                    ToastUtils.showShort("请输入省份城市开户支行");
//                    return;
//                }
                if (TextUtils.isEmpty(code)) {
                    ToastUtils.showShort("请输入短信验证码");
                    return;
                }
                if (bankInfo == null) {
                    MvpPre.addZFB(name,bankId, code);
                } else {
                    //MvpPre.editBank(name, branchBank, phone, idCard, bankInfo.getId(), bankId, cityBankName, code);
                    MvpPre.addZFB(name,bankId, code);
                }
                break;
            case R.id.tv_smsCode:
                String phoneNumber = MyApplication.getInstance().getUser().getMobile();
                if (TextUtils.isEmpty(phoneNumber)) {
                    ToastUtils.showShort("请先绑定手机号码");
                    return;
                }
                MvpPre.getPhoneCode(phoneNumber);
                break;
            case R.id.tv_right:
                setEnnableEdit(true);
                break;
        }
    }

    private void setEnnableEdit(boolean ennable) {
        edName.setEnabled(ennable);
        edCard.setEnabled(ennable);
        edBankNumber.setEnabled(ennable);
        edBankName.setEnabled(ennable);
        edCityBankName.setEnabled(ennable);
        tvCode.setEnabled(ennable);
        tvSmsCode.setEnabled(ennable);
        tvAddBank.setEnabled(ennable);
        if (bankInfo != null) {
            if (ennable) {
                edName.setText(null);
                edCard.setText(null);
                edBankNumber.setText(null);
                edBankName.setText(null);
                edCityBankName.setText(null);
            } else {
                edName.setText(bankInfo.getCardholder_hide());
                edCard.setText(bankInfo.getCard_number_hide());
                edBankNumber.setText(bankInfo.getBank_num_hide());
            }
        }
    }


    @Override
    public void showLoadings() {
        showLoading();
    }

    @Override
    public void disLoadings() {
        disLoading();
    }


    private boolean checkBankCard(String bankCard) {
        if (bankCard.length() < 15 || bankCard.length() > 19) {
            return false;
        }
        char bit = getBankCardCheckCode(bankCard.substring(0, bankCard.length() - 1));
        if (bit == 'N') {
            return false;
        }
        return bankCard.charAt(bankCard.length() - 1) == bit;
    }

    public char getBankCardCheckCode(String nonCheckCodeBankCard) {
        if (nonCheckCodeBankCard == null || nonCheckCodeBankCard.trim().length() == 0
                || !nonCheckCodeBankCard.matches("\\d+")) {
            //如果传的不是数据返回N
            return 'N';
        }
        char[] chs = nonCheckCodeBankCard.trim().toCharArray();
        int luhmSum = 0;
        for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if (j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhmSum += k;
        }
        return (luhmSum % 10 == 0) ? '0' : (char) ((10 - luhmSum % 10) + '0');
    }


    @Override
    public void addBankSuccess() {
        ToastUtils.showShort("绑定支付宝成功");
        finish();
    }

    private CountDownTimer mTimer;

    @Override
    public void getPhoneCodeSuccess() {
        ToastUtils.showShort("短信验证码发送成功请注意查收");
        tvSmsCode.setClickable(false);
        mTimer = new CountDownTimer(60000L, 1000L) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (tvSmsCode != null) {
                    tvSmsCode.setText(millisUntilFinished / 1000 + "秒后重发");
                }
            }

            @Override
            public void onFinish() {
                tvSmsCode.setClickable(true);
                tvSmsCode.setText("重发验证码");
            }
        };
        mTimer.start();
    }

    @Override
    public void editBankSuccess() {
        ToastUtils.showShort("绑定支付宝成功");
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }
}
