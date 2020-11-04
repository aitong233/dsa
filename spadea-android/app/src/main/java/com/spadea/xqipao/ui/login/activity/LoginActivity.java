package com.spadea.xqipao.ui.login.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.spadea.yuyin.MyApplication;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.PreferencesUtils;
import com.spadea.yuyin.util.utilcode.BarUtils;
import com.spadea.yuyin.util.utilcode.ToastUtils;
import com.spadea.xqipao.common.aroute.ARouters;
import com.spadea.xqipao.data.even.LoginFinishEvent;
import com.spadea.xqipao.ui.login.contacter.LoginContacter;
import com.spadea.xqipao.ui.login.presenter.LoginPresenter;
import com.spadea.xqipao.ui.base.view.BaseActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = ARouters.ME_LOGIN, name = "登录")
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContacter.View {


    @Autowired
    public String mobile;

    @BindView(R.id.ed_phone)
    EditText mEdPhone;
    @BindView(R.id.rel_phone)
    RelativeLayout mRelPhone;

    @BindView(R.id.ed_password)
    EditText mEdPassword;
    @BindView(R.id.rl_code)
    RelativeLayout mRlCode;

    @BindView(R.id.tv_login)
    TextView mTvLogin;


    public LoginActivity() {
        super(R.layout.activity_login);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void finishEvent(LoginFinishEvent event) {
        finish();
    }


    @Override
    protected void initView() {
        BarUtils.setStatusBarAlpha(this, 0);
        mEdPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setUpLoginBtn();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mEdPhone.setOnFocusChangeListener(new View.
                OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    mRelPhone.setSelected(true);
                } else {
                    // 此处为失去焦点时的处理内容
                    mRelPhone.setSelected(false);
                }
            }
        });
        mEdPassword.setOnFocusChangeListener(new View.
                OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // 此处为得到焦点时的处理内容
                    mRlCode.setSelected(true);
                } else {
                    // 此处为失去焦点时的处理内容
                    mRlCode.setSelected(false);
                }
            }
        });
        mEdPhone.setText(PreferencesUtils.getString(MyApplication.getInstance(), "mobile"));
        if (!TextUtils.isEmpty(mobile)) {
            mEdPhone.setText(mobile);
        }
    }

    private void setUpLoginBtn() {
        String text = mEdPhone.getText().toString();
        if (text.length() == 11) {
            mTvLogin.setEnabled(true);
            mTvLogin.setAlpha(1f);
        } else {
            mTvLogin.setAlpha(0.3f);
            mTvLogin.setEnabled(false);
        }
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
        ARouter.getInstance().build(ARouters.INPUT_CODE).withString("mobile", phoneNumber).navigation();
    }


    @OnClick({R.id.tv_code_text, R.id.tv_login, R.id.iv_qq,
            R.id.iv_weixin, R.id.tv_yhxy, R.id.tv_ysxy})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_code_text:
                Intent intent = new Intent(this, CodeLoginActivity.class);
                intent.putExtra("mobile", mEdPhone.getText().toString());
                startActivity(intent);
                finish();
                break;
            case R.id.tv_login:
                String phoneNumber = mEdPhone.getText().toString().trim();
                String password = mEdPassword.getText().toString().trim();
                if (TextUtils.isEmpty(phoneNumber)) {
                    ToastUtils.showShort("请输入手机号");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    ToastUtils.showShort("请输入登录密码");
                    return;
                }
                MvpPre.login(phoneNumber, password, "", 1);
                break;
            case R.id.iv_qq:
                MvpPre.qqLogin();
                break;
            case R.id.iv_weixin:
                MvpPre.wechatLogin();
                break;
            case R.id.tv_yhxy:
                MvpPre.ysxl();
                break;
            case R.id.tv_ysxy:
                MvpPre.yhxy();
                break;
            default:
                break;
        }
    }

}
