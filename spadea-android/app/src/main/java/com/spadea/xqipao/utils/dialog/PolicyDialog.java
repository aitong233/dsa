package com.spadea.xqipao.utils.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.spadea.yuyin.MyApplication;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.utilcode.SpanUtils;
import com.spadea.xqipao.common.Constant;
import com.spadea.xqipao.common.aroute.ARouters;

import butterknife.BindView;
import butterknife.OnClick;

public class PolicyDialog extends BaseDialog {


    @BindView(R.id.tv_text)
    TextView tvText;
    @BindView(R.id.tv_agree)
    TextView tvAgree;
    @BindView(R.id.tv_exit)
    TextView tvExit;

    private PolicyClickListener mPolicyClickListener;

    public PolicyDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public int getLayout() {
        return R.layout.dialog_polic;
    }

    @Override
    public void initView() {
        SpanUtils spanUtils = new SpanUtils();
        ClickableSpan clickSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                ARouter.getInstance().build(ARouters.H5).withString("url", Constant.URL.URL_USER_YHXY).navigation();
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                ds.setColor(MyApplication.getInstance().getResources().getColor(R.color.color_main));
                ds.setUnderlineText(true);
            }
        };
        ClickableSpan ysClickSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                ARouter.getInstance().build(ARouters.H5).withString("url", Constant.URL.URL_USER_YSXY).navigation();
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                ds.setColor(MyApplication.getInstance().getResources().getColor(R.color.color_main));
                ds.setUnderlineText(true);
            }
        };
        spanUtils.append("在使用我们的产品和服务之前，请您先阅读并了解").append("《用户协议》").setClickSpan(clickSpan).append("和").append("《隐私协议》").setClickSpan(ysClickSpan).append("。");
        tvText.setText(spanUtils.create());
        tvText.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public void initData() {
        setCanceledOnTouchOutside(false);
    }

    @OnClick({R.id.tv_agree, R.id.tv_exit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_agree:
                if (mPolicyClickListener != null) {
                    mPolicyClickListener.policyAgree();
                }
                break;
            case R.id.tv_exit:
                if (mPolicyClickListener != null) {
                    mPolicyClickListener.policyExit();
                }
                break;
        }
        this.dismiss();
    }


    public void setmPolicyClickListener(PolicyClickListener policyClickListener) {
        this.mPolicyClickListener = policyClickListener;
    }


    @Override
    public boolean onKeyDown(int keyCode, @NonNull KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            return true;
        }
        if (keyCode == KeyEvent.KEYCODE_SEARCH) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public interface PolicyClickListener {
        void policyAgree();

        void policyExit();
    }
}
