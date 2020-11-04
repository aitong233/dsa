package com.spadea.xqipao.ui.login.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.spadea.xqipao.common.aroute.ARouters;
import com.spadea.xqipao.data.even.LoginFinishEvent;
import com.spadea.xqipao.ui.login.contacter.LoginContacter;
import com.spadea.xqipao.ui.login.presenter.LoginPresenter;
import com.tuo.customview.VerificationCodeView;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.utilcode.BarUtils;
import com.spadea.yuyin.util.utilcode.ToastUtils;
import com.spadea.xqipao.ui.base.view.BaseActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui.login.activity
 * 创建人 王欧
 * 创建时间 2020/7/2 5:46 PM
 * 描述 describe
 */
@Route(path = ARouters.INPUT_CODE)
public class InputCodeActivity extends BaseActivity<LoginPresenter> implements LoginContacter.View, VerificationCodeView.InputCompleteListener {

    @BindView(R.id.verificationcodeview)
    VerificationCodeView mVerificationcodeview;

    @BindView(R.id.tv_send_code)
    TextView mTvSendCode;

    @BindView(R.id.tv_login)
    TextView mTvLogin;

    @BindView(R.id.tv_login_ts)
    TextView mTvLoginTips;

    private String usercode;
    @Autowired
    public String mobile;

    public InputCodeActivity() {
        super(R.layout.activity_input_code);
    }

    @Override
    protected void initData() {
        sendCodeSuccess(mobile);
    }

    @Override
    protected void initView() {
        BarUtils.setStatusBarAlpha(this, 0);
        mVerificationcodeview.setInputCompleteListener(this);
        mTvLoginTips.setText(String.format("验证码已发送至%s", mobile));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void finishEvent(LoginFinishEvent event) {
        finish();
    }


    @Override
    protected LoginPresenter bindPresenter() {
        return new LoginPresenter(this, this);
    }


    @Override
    public void showLoadings() {
        showCommonLoading();
    }

    @Override
    public void disLoadings() {
        disCommonLoading();
    }

    @Override
    public void sendCodeSuccess(String phoneNumber) {
        ToastUtils.showShort("短信验证码发送成功请注意查收");
        mTvSendCode.setEnabled(false);
        mTvSendCode.setAlpha(0.3f);
        releaseTimer();
        if (mTimer != null) {
            mTimer.cancel();
        }
        mTimer = new CountDownTimer(60000L, 1000L) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (mTvSendCode != null) {
                    mTvSendCode.setText(String.format("重新发送（%s）", millisUntilFinished / 1000));
                }
            }

            @Override
            public void onFinish() {
                mTvSendCode.setAlpha(1f);
                mTvSendCode.setEnabled(true);
                mTvSendCode.setText("重新发送");
            }
        };
        mTimer.start();
    }

    @OnClick({R.id.iv_code_back, R.id.tv_send_code, R.id.tv_login, R.id.tv_yhxy, R.id.tv_ysxy, R.id.iv_qq, R.id.iv_weixin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_code_back:
                finish();
                break;
            case R.id.tv_send_code:
                if (TextUtils.isEmpty(mobile)) {
                    ToastUtils.showShort("请输入手机号");
                    return;
                }
                MvpPre.sendCode(mobile, 1);
                break;
            case R.id.tv_login:
                if (TextUtils.isEmpty(usercode)) {
                    ToastUtils.showShort("请输入短信验证码");
                    return;
                }
                MvpPre.login(mobile, "", usercode, 2);
                break;
            case R.id.tv_yhxy:
                MvpPre.yhxy();
                break;
            case R.id.tv_ysxy:
                MvpPre.ysxl();
                break;
            case R.id.iv_qq:
                MvpPre.qqLogin();
                break;
            case R.id.iv_weixin:
                MvpPre.wechatLogin();
                break;
        }
    }

    @Override
    public void inputComplete() {
        mTvLogin.setEnabled(true);
        mTvLogin.setAlpha(1f);
        usercode = mVerificationcodeview.getInputContent();
    }

    @Override
    public void deleteContent() {
        mTvLogin.setAlpha(0.3f);
        mTvLogin.setEnabled(false);
    }

    @Override
    protected void onDestroy() {
        releaseTimer();
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    private CountDownTimer mTimer;

    private void releaseTimer() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }
}
