package com.spadea.xqipao.widget;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.spadea.yuyin.MyApplication;
import com.spadea.yuyin.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.widget
 * 创建人 王欧
 * 创建时间 2020/5/15 5:50 PM
 * 描述 describe
 */
public class IdentifyCodeView extends LinearLayout implements View.OnFocusChangeListener {
    @BindView(R.id.ll_code)
    LinearLayout mLL;
    @BindView(R.id.iv_icon)
    ImageView mIvIcon;
    @BindView(R.id.et_code)
    EditText mEtCode;
    @BindView(R.id.tv_get_code)
    TextView mTvGetCode;

    private GetCodeClickCallBack mCallBack;

    private CountDownTimer mTimer;

    public void setCallBack(GetCodeClickCallBack callBack) {
        mCallBack = callBack;
    }

    public IdentifyCodeView(Context context) {
        this(context, null);
    }

    public IdentifyCodeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_identify_code, this);
        ButterKnife.bind(this);
        mEtCode.setOnFocusChangeListener(this);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            mIvIcon.setColorFilter(MyApplication.getInstance().getResources().getColor(R.color.color_main));
            mLL.setBackgroundResource(R.drawable.bg_identify_edit_focus);
        } else {
            mIvIcon.setColorFilter(MyApplication.getInstance().getResources().getColor(R.color.color_9c9c9c));
            mLL.setBackgroundResource(R.drawable.bg_r5_white);
        }
    }

    public void startCountDown() {
        mTvGetCode.setClickable(false);
        mTimer = new CountDownTimer(60000L, 1000L) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (mTvGetCode != null) {
                    mTvGetCode.setText(millisUntilFinished / 1000 + "秒后重发");
                }
            }

            @Override
            public void onFinish() {
                mTvGetCode.setClickable(true);
                mTvGetCode.setText("重发验证码");
            }
        };
        mTimer.start();
    }

    @OnClick(R.id.tv_get_code)
    public void onGetCodeClick(View view) {
        if (mCallBack != null) {
            mCallBack.onGetCodeClick();
        }
    }

    public String getText() {
        return mEtCode.getText().toString();
    }

    public interface GetCodeClickCallBack {
        void onGetCodeClick();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }
}
