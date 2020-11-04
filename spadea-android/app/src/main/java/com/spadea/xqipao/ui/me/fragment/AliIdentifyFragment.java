package com.spadea.xqipao.ui.me.fragment;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.spadea.yuyin.MyApplication;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.utilcode.SpanUtils;
import com.spadea.yuyin.util.utilcode.ToastUtils;
import com.spadea.xqipao.data.NameAuthModel;
import com.spadea.xqipao.ui.base.view.BaseFragment;
import com.spadea.xqipao.ui.me.contacter.NameIdentifyContacts;
import com.spadea.xqipao.ui.me.presenter.NameIdentifyPresenter;
import com.spadea.xqipao.widget.IdentifyCodeView;
import com.spadea.xqipao.widget.IdentifyEditView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui.me.fragment
 * 创建人 王欧
 * 创建时间 2020/5/15 4:09 PM
 * 描述 describe
 */
public class AliIdentifyFragment extends BaseFragment<NameIdentifyPresenter> implements NameIdentifyContacts.View, IdentifyCodeView.GetCodeClickCallBack {
    @BindView(R.id.iev_name)
    IdentifyEditView mIevName;
    @BindView(R.id.iev_no)
    IdentifyEditView mIevNo;
    @BindView(R.id.iev_phone)
    IdentifyEditView mIevPhone;
    @BindView(R.id.tv_tip_title)
    TextView mTvTipTitle;
    @BindView(R.id.tv_remark1)
    TextView mTvRemark1;
    @BindView(R.id.tv_remark2)
    TextView mTvRemark2;
    @BindView(R.id.ll_tips)
    LinearLayout mLlTips;
    @BindView(R.id.tv_submit)
    TextView mTvSubmit;
    @BindView(R.id.icv_code)
    IdentifyCodeView mIcvCode;


    @Override
    protected NameIdentifyPresenter bindPresenter() {
        return new NameIdentifyPresenter(this, getActivity());
    }

    @Override
    protected void initData() {
        mTvRemark1.setText(new SpanUtils().append("支付宝认证（自动审核）：").setForegroundColor(Color.parseColor("#9C9C9C")).setBold().append("需要提供姓名、身份证号、手机号、手机号验证码，跳转支付宝认证页面").create());
        mTvRemark2.setText(new SpanUtils().append("手持身份证认证（人工审核）：").setForegroundColor(Color.parseColor("#9C9C9C")).setBold().append("需要提供姓名、身份证、手机号、手机验证码，身份证照片（反面、正面、手持身份证照片）审核时长：1-3个工作日").create());
    }

    @Override
    protected void initView(View rootView) {
        mIcvCode.setCallBack(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragement_ali_identify;
    }

    @Override
    public void showLoadings() {
        showLoading();
    }

    @Override
    public void disLoadings() {
        disLoading();
    }

    @OnClick({R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_submit:
                String name = mIevName.getText();
                String idNo = mIevNo.getText();
                if (TextUtils.isEmpty(name)) {
                    ToastUtils.showShort("请输入姓名");
                    return;
                }
                if (TextUtils.isEmpty(idNo)) {
                    ToastUtils.showShort("请输入身份证号");
                    return;
                }
                NameAuthModel model = new NameAuthModel();
                model.setFullName(name);
                model.setIdCard(idNo);
                model.setUserId(MyApplication.getInstance().getUser().getUser_id());
                MvpPre.doAuth(model);
                break;
        }
    }

    @Override
    public void sendCodeSuccess() {
        if (mIcvCode != null) {
            mIcvCode.startCountDown();
        }
    }

    @Override
    public void uploadImageSuccess(String imageUrl, int type) {

    }

    @Override
    public void onGetCodeClick() {
        String phone = mIevPhone.getText();
        if (TextUtils.isEmpty(phone)) {
            ToastUtils.showShort("请输入手机号");
            return;
        }
        MvpPre.sendCode(phone, 6);
    }
}
