package com.spadea.xqipao.ui.me.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.spadea.xqipao.ui.base.view.BaseActivity;
import com.spadea.xqipao.ui.me.contacter.SecondLevelPasswordContacter;
import com.spadea.xqipao.ui.me.presenter.SecondLevelPasswordPresenter;
import com.spadea.yuyin.MyApplication;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.utilcode.KeyboardUtils;
import com.spadea.yuyin.util.utilcode.ToastUtils;
import com.qpyy.libcommon.bean.UserBean;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui.me.activity
 * 创建人 王欧
 * 创建时间 2020/5/8 10:04 AM
 * 描述 describe
 */
public class SecondPasswordFirstSetActivity extends BaseActivity<SecondLevelPasswordPresenter> implements SecondLevelPasswordContacter.View {
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.et_pwd)
    EditText mEtPwd;
    @BindView(R.id.et_pwd2)
    EditText mEtPwd2;

    public SecondPasswordFirstSetActivity() {
        super(R.layout.activity_second_password_fisrt_set);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mTvTitle.setText("设置二级密码");
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


    @Override
    public void sendCodeSuccess() {
    }

    @Override
    public void settingPasswordSuess() {
        UserBean user = MyApplication.getInstance().getUser();
        user.setSecond_password(1);
        MyApplication.getInstance().setUser(user);
        finish();
    }

    @OnClick({R.id.iv_left, R.id.tv_left, R.id.btn_submit})
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
                String password = mEtPwd.getText().toString();
                String checkPassword = mEtPwd2.getText().toString();
                if (TextUtils.isEmpty(password)) {
                    ToastUtils.showShort("请输入二级密码");
                    return;
                }
                if (!password.equals(checkPassword)) {
                    ToastUtils.showShort("两次输入的密码不一致");
                    return;
                }
                MvpPre.settingPassword(MyApplication.getInstance().getUser().getMobile(), password, null);
                break;
            case R.id.tv_code_qa:
                break;
        }
    }
}
