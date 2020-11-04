package com.spadea.xqipao.ui.me.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.spadea.yuyin.MyApplication;
import com.spadea.yuyin.R;
import com.spadea.xqipao.ui.base.view.BaseActivity;
import com.spadea.xqipao.ui.me.contacter.ChangeMobileVerifyContacts;
import com.spadea.xqipao.ui.me.presenter.ChangeMobileVerifyPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui.me.activity
 * 创建人 王欧
 * 创建时间 2020/3/26 5:05 PM
 * 描述 describe
 */
public class ChangeMobileVerifyActivity extends BaseActivity<ChangeMobileVerifyPresenter> implements ChangeMobileVerifyContacts.View {
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.et_code)
    EditText mEtCode;
    @BindView(R.id.tv_phone)
    TextView mTvPhone;
    private String mPhone;

    public ChangeMobileVerifyActivity() {
        super(R.layout.activity_change_mobile_verify);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mPhone = getIntent().getStringExtra("phone");
        mTvTitle.setText("填写验证码");
        mTvPhone.setText(mPhone);
    }

    @Override
    protected ChangeMobileVerifyPresenter bindPresenter() {
        return new ChangeMobileVerifyPresenter(this, this);
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
    public void bindSuccess() {
        MyApplication.getInstance().reLogin();
    }

    @OnClick({R.id.iv_left, R.id.tv_left, R.id.btn_submit, R.id.tv_code_qa})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
            case R.id.tv_left:
                onBackPressed();
                break;
            case R.id.btn_submit:
                MvpPre.bindingMobile(mPhone, mEtCode.getText().toString().trim());
                break;
            case R.id.tv_code_qa:
                // TODO: 2020/3/27 收不到验证码
                break;
        }
    }
}
