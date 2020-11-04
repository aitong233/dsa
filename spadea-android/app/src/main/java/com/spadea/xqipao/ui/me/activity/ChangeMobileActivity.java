package com.spadea.xqipao.ui.me.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.spadea.xqipao.common.aroute.ARouters;
import com.spadea.xqipao.ui.base.view.BaseActivity;
import com.spadea.xqipao.ui.me.contacter.ChangeMobileContacts;
import com.spadea.xqipao.ui.me.presenter.ChangeMobilePresenter;
import com.spadea.yuyin.MyApplication;
import com.spadea.yuyin.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui.me.activity
 * 创建人 王欧
 * 创建时间 2020/3/26 5:36 PM
 * 描述 describe
 */
public class ChangeMobileActivity extends BaseActivity<ChangeMobilePresenter> implements ChangeMobileContacts.View {
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_country)
    TextView mTvCountry;
    @BindView(R.id.tv_country_code)
    TextView mTvCountryCode;
    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.tv_tip)
    TextView mTvTip;

    String country="中国";
    String countryCode="86";

    public ChangeMobileActivity() {
        super(R.layout.activity_change_mobile);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mTvTitle.setText("手机号绑定");
        mTvTip.setText(String.format("更改手机后，下次登录可使用新手机号登录，当前手机号：%s", MyApplication.getInstance().getUser().getMobile()));
        mTvCountryCode.setText("+"+countryCode);
        mTvCountry.setText(country);
    }

    @Override
    protected ChangeMobilePresenter bindPresenter() {
        return new ChangeMobilePresenter(this, this);
    }

    @Override
    public void getSmsCodeSuccess(String mobile) {
        Intent intent=new Intent(this, ChangeMobileVerifyActivity.class);
        intent.putExtra("phone",mobile);
        startActivity(intent);
    }

    @Override
    public void showLoadings() {
        showLoading();
    }

    @Override
    public void disLoadings() {
        disLoading();
    }

    @OnClick({R.id.iv_left, R.id.ll_country, R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                onBackPressed();
                break;
            case R.id.ll_country:
                ARouter.getInstance().build(ARouters.ME_COUNTRYSELECTACTIVITY).navigation(this,100);
                break;
            case R.id.btn_next:
                String phone = mEtPhone.getText().toString();
                MvpPre.smsCode(phone, 4);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==100 && data!=null){
            country=data.getStringExtra("country");
            countryCode=data.getStringExtra("code");
            mTvCountry.setText(country);
            mTvCountryCode.setText("+"+countryCode);
        }
    }
}
