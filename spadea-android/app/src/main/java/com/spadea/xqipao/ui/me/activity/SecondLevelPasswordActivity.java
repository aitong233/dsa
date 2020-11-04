package com.spadea.xqipao.ui.me.activity;

import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.spadea.yuyin.MyApplication;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.utilcode.KeyboardUtils;
import com.spadea.yuyin.util.utilcode.ToastUtils;
import com.qpyy.libcommon.bean.UserBean;
import com.spadea.xqipao.ui.base.view.BaseActivity;
import com.spadea.xqipao.ui.me.contacter.SecondLevelPasswordContacter;
import com.spadea.xqipao.ui.me.presenter.SecondLevelPasswordPresenter;

import butterknife.BindView;
import butterknife.OnClick;

public class SecondLevelPasswordActivity extends BaseActivity<SecondLevelPasswordPresenter> implements SecondLevelPasswordContacter.View {


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
    @BindView(R.id.ll_phone)
    LinearLayout mLLPhone;
    @BindView(R.id.rl_code)
    RelativeLayout mRLCode;

    public SecondLevelPasswordActivity() {
        super(R.layout.activity_second_level_password);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mTvTitle.setText("设置二级密码");
        if (MyApplication.getInstance().getUser() != null && !TextUtils.isEmpty(MyApplication.getInstance().getUser().getMobile())) {
            mBtnCode.setClickable(true);
            mTvPhone.setText(MyApplication.getInstance().getUser().getMobile());
        } else {
            mBtnCode.setClickable(false);
            mTvPhone.setText("请先绑定手机号码");
        }
    }

    @Override
    protected SecondLevelPasswordPresenter bindPresenter() {
        return new SecondLevelPasswordPresenter(this, this);
    }

    @Override
    public void showLoadings() {
        showLoading();
    }

    @Override
    public void disLoadings() {
        disLoading();
    }

    private CountDownTimer mTimer;

    @Override
    public void sendCodeSuccess() {
        mBtnCode.setClickable(false);
        mTimer = new CountDownTimer(60000L, 1000L) {
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
        mTimer.start();
    }

    @Override
    public void settingPasswordSuess() {
        UserBean user = MyApplication.getInstance().getUser();
        user.setSecond_password(1);
        MyApplication.getInstance().setUser(user);
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

    @OnClick({R.id.iv_left, R.id.tv_left, R.id.btn_code, R.id.btn_submit, R.id.tv_code_qa})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
            case R.id.tv_left:
                onBackPressed();
                break;
            case R.id.btn_code:
                MvpPre.sendCode(MyApplication.getInstance().getUser().getMobile(), 5);
                break;
            case R.id.btn_submit:
                KeyboardUtils.hideSoftInput(this);
                String code = mEtCode.getText().toString().trim();
                String password = mEtPwd.getText().toString();
                String chenkPassword = mEtPwd2.getText().toString();

                if (TextUtils.isEmpty(code)) {
                    ToastUtils.showShort("请输入短信验证码");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    ToastUtils.showShort("请输入二级密码");
                    return;
                }
                if (!password.equals(chenkPassword)) {
                    ToastUtils.showShort("两次输入的密码不一致");
                    return;
                }
                MvpPre.settingPassword(MyApplication.getInstance().getUser().getMobile(), password, code);
                break;
            case R.id.tv_code_qa:
                break;
        }
    }
}
