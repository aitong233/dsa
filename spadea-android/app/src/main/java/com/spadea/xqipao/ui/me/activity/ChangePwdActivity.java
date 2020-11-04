package com.spadea.xqipao.ui.me.activity;

import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.spadea.xqipao.ui.base.view.BaseActivity;
import com.spadea.xqipao.ui.me.contacter.ChangePwdContacts;
import com.spadea.xqipao.ui.me.presenter.ChangePwdPresenter;
import com.spadea.yuyin.MyApplication;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.utilcode.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui.me.activity
 * 创建人 王欧
 * 创建时间 2020/3/27 11:14 AM
 * 描述 describe
 */
public class ChangePwdActivity extends BaseActivity<ChangePwdPresenter> implements ChangePwdContacts.View {
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_phone)
    TextView mTvPhone;
    @BindView(R.id.et_code)
    EditText mEtCode;
    @BindView(R.id.et_pwd)
    EditText mEtPwd;
    @BindView(R.id.et_pwd2)
    EditText mEtPwd2;
    @BindView(R.id.btn_code)
    Button mBtnCode;

    private CountDownTimer mCountDownTimer;

    public ChangePwdActivity() {
        super(R.layout.activity_change_pwd);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mTvTitle.setText("重置密码");
        if (MyApplication.getInstance().getUser() != null && !TextUtils.isEmpty(MyApplication.getInstance().getUser().getMobile())) {
            mBtnCode.setClickable(true);
            mTvPhone.setText(MyApplication.getInstance().getUser().getMobile());
        } else {
            mBtnCode.setClickable(false);
            mTvPhone.setText("请先绑定手机号码");
        }
    }

    @Override
    protected ChangePwdPresenter bindPresenter() {
        return new ChangePwdPresenter(this, this);
    }

    @Override
    public void success() {
        MyApplication.getInstance().reLogin();
    }

    @Override
    public void smsCodeSuccess() {
        mBtnCode.setClickable(false);
        mCountDownTimer = new CountDownTimer(60000L, 1000L) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (mBtnCode != null) {
                    mBtnCode.setText(millisUntilFinished / 1000 + "秒后重发");
                }
            }

            @Override
            public void onFinish() {
                mBtnCode.setClickable(true);
                mBtnCode.setText("重发验证码");
            }
        };
        mCountDownTimer.start();
    }

    @Override
    public void showLoadings() {
        showLoading();
    }

    @Override
    public void disLoadings() {
        disLoading();
    }

    @OnClick({R.id.iv_left, R.id.tv_left, R.id.btn_submit, R.id.tv_code_qa, R.id.btn_code})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
            case R.id.tv_left:
                onBackPressed();
                break;
            case R.id.btn_submit:
                String code = mEtCode.getText().toString().trim();
                String pwd = mEtPwd.getText().toString().trim();
                String pwd2 = mEtPwd2.getText().toString().trim();
                if (TextUtils.isEmpty(code)) {
                    ToastUtils.showShort("请输入短信验证码");
                    return;
                }
                if (TextUtils.isEmpty(pwd) || TextUtils.isEmpty(pwd2)) {
                    ToastUtils.showShort("请输入密码");
                    return;
                }
                if (!pwd.equals(pwd2)) {
                    ToastUtils.showShort("两次输入的密码不一致");
                    return;
                }
                MvpPre.resetPassword(MyApplication.getInstance().getUser().getMobile(), pwd, mEtCode.getText().toString().trim());
                break;
            case R.id.tv_code_qa:
                // TODO: 2020/3/27 收不到验证码
                break;
            case R.id.btn_code:
                MvpPre.smsCode(MyApplication.getInstance().getUser().getMobile());
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
            mCountDownTimer = null;
        }
    }
}
