package com.spadea.xqipao.ui.me.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.spadea.yuyin.MyApplication;
import com.spadea.yuyin.R;
import com.spadea.yuyin.ui.fragment2.setting.mobilebind.MobileBindActivity;
import com.spadea.yuyin.ui.fragment2.setting.nameverify.NameVerifyActivity;
import com.spadea.yuyin.util.utilcode.ToastUtils;
import com.spadea.xqipao.common.aroute.ARouters;
import com.spadea.xqipao.ui.base.view.BaseAppCompatActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui.me.activity
 * 创建人 王欧
 * 创建时间 2020/3/26 3:13 PM
 * 描述 describe
 */
public class AccountSecurityActivity extends BaseAppCompatActivity {
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_id)
    TextView mTvId;
    @BindView(R.id.tv_phone_bind)
    TextView mTvPhoneBind;
    @BindView(R.id.tv_password)
    TextView mTvPassword;
    @BindView(R.id.tv_second_pwd)
    TextView mTvSecondPwd;
    @BindView(R.id.tv_name_verify)
    TextView mTvNameVerify;
    @BindView(R.id.iv_name_verify)
    ImageView mIvNameVerify;

    public AccountSecurityActivity() {
        super(R.layout.activity_account_security);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mTvTitle.setText("账号与安全");
        mTvId.setHint(MyApplication.getInstance().getUser().getUser_code());
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (TextUtils.isEmpty(MyApplication.getInstance().getUser().getMobile())) {
            mTvPhoneBind.setHint("未绑定");
        } else {
            mTvPhoneBind.setHint(MyApplication.getInstance().getUser().getMobile());
        }
        if (TextUtils.isEmpty(MyApplication.getInstance().getUser().getIdentity_number())) {
            mTvNameVerify.setHint("未认证");
            mIvNameVerify.setVisibility(View.VISIBLE);
        } else {
            mTvNameVerify.setHint("已认证");
            mIvNameVerify.setVisibility(View.GONE);
        }
        if (MyApplication.getInstance().getUser().getIs_password() == 1) {
            mTvPassword.setHint("已设置");
        } else {
            mTvPassword.setHint("未设置");
        }
        if (MyApplication.getInstance().getUser().getSecond_password() == 0) {
            mTvSecondPwd.setHint("未设置");
        } else {
            mTvSecondPwd.setHint("已设置");
        }
    }

    @OnClick({R.id.iv_left, R.id.tv_left, R.id.ll_phone_bind, R.id.ll_password, R.id.ll_second_pwd, R.id.ll_name_verify,R.id.lin_security_center})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
            case R.id.tv_left:
                onBackPressed();
                break;
            case R.id.ll_phone_bind:
                if (TextUtils.isEmpty(MyApplication.getInstance().getUser().getMobile())) {
                    startActivity(new Intent(this, MobileBindActivity.class));
                } else {
                    startActivity(new Intent(this, ChangeMobileActivity.class));
                }
                break;
            case R.id.ll_password:
                String phonenumber = MyApplication.getInstance().getUser().getMobile();
                if (TextUtils.isEmpty(phonenumber)) {
                    return;
                }
                startActivity(new Intent(this, ChangePwdActivity.class));
                break;
            case R.id.ll_second_pwd:
                String phoneNumber = MyApplication.getInstance().getUser().getMobile();
                if (MyApplication.getInstance().getUser().getSecond_password() == 0) {
                    startActivity(new Intent(this, SecondPasswordFirstSetActivity.class));
                } else {
                    if (TextUtils.isEmpty(phoneNumber)) {
                        ToastUtils.showShort("请先绑定手机号");
                        return;
                    }
                    startActivity(new Intent(this, SecondLevelPasswordActivity.class));
                }
                break;
            case R.id.ll_name_verify:
                if (TextUtils.isEmpty(MyApplication.getInstance().getUser().getIdentity_number())) {
                    ARouter.getInstance().build(ARouters.NAME_IDENTIFY).navigation();
                } else {
                    startActivity(new Intent(this, NameVerifyActivity.class));
                }
                break;
            case R.id.lin_security_center:
                if (TextUtils.isEmpty(MyApplication.getInstance().getUser().getMobile())) {
                    ToastUtils.showLong("未绑定手机号");
                } else {
                    startActivity(new Intent(this, SecurityCenterActivity.class));
                }
                break;
            default:break;
        }
    }
}
