package com.qpyy.module_main.activity;

import com.qpyy.libcommon.base.BaseMvpActivity;
import com.qpyy.libcommon.bean.UserBean;
import com.qpyy.module_main.R;
import com.qpyy.module_main.contacts.PasswordLoginContacts;
import com.qpyy.module_main.presenter.PasswordLoginPresenter;


public class PasswordLoginActivity extends BaseMvpActivity<PasswordLoginPresenter> implements PasswordLoginContacts.View {


    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.main_activity_password_login;
    }

    @Override
    protected PasswordLoginPresenter bindPresenter() {
        return new PasswordLoginPresenter(this, this);
    }

    @Override
    public void loginSuccess(UserBean userBean) {

    }

    @Override
    public void sendCodeSuccess() {

    }
}
