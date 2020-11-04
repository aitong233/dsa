package com.spadea.xqipao.ui.me.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.TextAppearanceSpan;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.spadea.yuyin.MyApplication;
import com.spadea.yuyin.R;
import com.spadea.xqipao.common.Constant;
import com.spadea.xqipao.common.aroute.ARouters;
import com.spadea.xqipao.data.LogoutReasonModel;
import com.spadea.xqipao.ui.base.view.BaseActivity;
import com.spadea.xqipao.ui.me.contacter.LogoutAccountContacts;
import com.spadea.xqipao.ui.me.presenter.LogoutAccountPresenter;

import butterknife.BindView;
import butterknife.OnClick;

public class LogoutAccountActivity extends BaseActivity<LogoutAccountPresenter> implements LogoutAccountContacts.View {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.rel_total_bar)
    RelativeLayout relTotalBar;
    @BindView(R.id.tv_la_sm_title1)
    TextView tvLaSmTitle1;
    @BindView(R.id.tv_la_sm_title2)
    TextView tvLaSmTitle2;
    @BindView(R.id.tv_la_sm_title3)
    TextView tvLaSmTitle3;
    @BindView(R.id.tv_la_sm_title4)
    TextView tvLaSmTitle4;
    @BindView(R.id.iv_la_ico_bs)
    ImageView ivLaIcoBs;
    @BindView(R.id.tv_la_ts_content1)
    TextView ivLaTsContent1;
    @BindView(R.id.cb_agreement)
    CheckBox cbAgreement;
    @BindView(R.id.tv_confirm_logout)
    TextView tvConfirmLogout;
    @BindView(R.id.tv_agreement)
    TextView tvAgreement;


    String reason;

    public LogoutAccountActivity() {
        super(R.layout.activity_logout_account);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        relTotalBar.setBackgroundResource(R.color.white);
        tvTitle.setText("注销账号");
        Paint paint1 = tvLaSmTitle1.getPaint();
        paint1.setFakeBoldText(true);
        Paint paint2 = tvLaSmTitle2.getPaint();
        paint2.setFakeBoldText(true);
        Paint paint3 = tvLaSmTitle3.getPaint();
        paint3.setFakeBoldText(true);
        Paint paint4 = tvLaSmTitle4.getPaint();
        paint4.setFakeBoldText(true);
        if (MyApplication.getInstance().getUser().getMobile() != null && MyApplication.getInstance().getUser().getMobile().length() > 10) {
            ivLaTsContent1.setText("将+86 " + MyApplication.getInstance().getUser().getMobile().replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2") + "所绑定的帐号注销");
        }
        tvConfirmLogout.setSelected(false);
        tvConfirmLogout.setEnabled(false);
        tvAgreement.setText(FontHighlighting("我已阅读并同意", "《黑桃A帐号注销须知》", R.style.logout_agreement1, R.style.logout_agreement2));
        tvAgreement.setMovementMethod(LinkMovementMethod.getInstance());
        MvpPre.getlogoutStatus(MyApplication.getInstance().getToken(), MyApplication.getInstance().getUser().getMobile());
    }


    @Override
    protected LogoutAccountPresenter bindPresenter() {
        return new LogoutAccountPresenter(this, this);
    }

    @Override
    public void showLoadings() {

    }

    @Override
    public void disLoadings() {

    }

    public SpannableStringBuilder FontHighlighting(String src1, String src2, int appearance1, int appearance2) {
        String content2 = src1 + src2;
        SpannableStringBuilder spanStr = new SpannableStringBuilder(content2);
        spanStr.setSpan(new TextAppearanceSpan(getBaseContext(), appearance1), 0, src1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spanStr.setSpan(new TextAppearanceSpan(getBaseContext(), appearance2), src1.length(), content2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spanStr.setSpan(new ClickableSpan() {
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(getBaseContext().getResources().getColor(R.color.color_6765FF));
                //设置是否有下划线
                ds.setUnderlineText(false);
            }

            @Override
            public void onClick(View view) {
                ARouter.getInstance().build(ARouters.H5).withString("url", Constant.URL.URL_LOGOUT_CANCELLATION).withString("title", "黑桃A帐号注销须知").navigation();
            }
        }, src1.length(), content2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spanStr;
    }


    @OnClick({R.id.iv_back, R.id.cb_agreement, R.id.tv_confirm_logout})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.cb_agreement:
                if (cbAgreement.isChecked()) {
                    tvConfirmLogout.setSelected(true);
                    tvConfirmLogout.setEnabled(true);
                } else {
                    tvConfirmLogout.setSelected(false);
                    tvConfirmLogout.setEnabled(false);
                }
                break;
            case R.id.tv_confirm_logout:
                if (TextUtils.isEmpty(reason)) {
                    startActivity(new Intent(this, LogoutAccountValidationActivity.class));
                } else {
                    ARouter.getInstance().build(ARouters.LOGOUT_CANNOT).withString("reason", reason).navigation();
                }
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void setlogoutStatus(LogoutReasonModel logoutReasonModel) {
        if(logoutReasonModel!=null&& !TextUtils.isEmpty(logoutReasonModel.getData())){
            reason=logoutReasonModel.getData();
        }
    }
}