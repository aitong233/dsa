package com.spadea.xqipao.ui.me.activity;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.contrarywind.listener.OnItemSelectedListener;
import com.contrarywind.view.WheelView;
import com.spadea.xqipao.ui.base.view.BaseActivity;
import com.spadea.xqipao.ui.me.contacter.LogoutAccountValidationContacts;
import com.spadea.xqipao.ui.me.presenter.LogoutAccountValidationPresenter;
import com.spadea.xqipao.ui.order.adapter.ArrayWheelAdapter;
import com.spadea.yuyin.MyApplication;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.utilcode.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class LogoutAccountValidationActivity extends BaseActivity<LogoutAccountValidationPresenter> implements LogoutAccountValidationContacts.View {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.rel_total_bar)
    RelativeLayout relTotalBar;
    @BindView(R.id.tv_lav_phone)
    TextView tvLavPhone;
    @BindView(R.id.tv_lav_send_code)
    TextView tvLavSendCode;
    @BindView(R.id.tv_lav_reason)
    TextView tvLavReason;
    @BindView(R.id.et_send_code)
    EditText etSendCode;

    private CountDownTimer mTimer;

    public LogoutAccountValidationActivity() {
        super(R.layout.activity_logout_account_validation);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        relTotalBar.setBackgroundResource(R.color.white);
        tvTitle.setText("身份验证");
        if (MyApplication.getInstance().getUser().getMobile() != null && MyApplication.getInstance().getUser().getMobile().length() > 10) {
            tvLavPhone.setText("将+86 " + MyApplication.getInstance().getUser().getMobile().replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"));
        }
    }

    @Override
    protected LogoutAccountValidationPresenter bindPresenter() {
        return new LogoutAccountValidationPresenter(this, this);
    }

    @Override
    public void showLoadings() {

    }

    @Override
    public void disLoadings() {

    }

    @OnClick({R.id.iv_back, R.id.tv_lav_send_code, R.id.tv_logout_confirm, R.id.lin_lav_reason})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_lav_send_code:
                if (TextUtils.isEmpty(MyApplication.getInstance().getUser().getMobile())) {
                    ToastUtils.showShort("未绑定手机号");
                    return;
                }
                MvpPre.sendCode(MyApplication.getInstance().getUser().getMobile(), 8);
                break;
            case R.id.tv_logout_confirm:
                if (TextUtils.isEmpty(tvLavReason.getText().toString().trim())) {
                    ToastUtils.showShort("请选择注销原因");
                    return;
                }
                if (TextUtils.isEmpty(MyApplication.getInstance().getUser().getMobile())) {
                    ToastUtils.showShort("未绑定手机号");
                    return;
                }
                if (TextUtils.isEmpty(etSendCode.getText().toString().trim())) {
                    ToastUtils.showShort("请填写邀请码");
                    return;
                }
                MvpPre.logoutReason(MyApplication.getInstance().getToken(),MyApplication.getInstance().getUser().getMobile()
                        ,tvLavReason.getText().toString().trim(),etSendCode.getText().toString().trim());
                break;
            case R.id.lin_lav_reason:
                new LogoutReasonView(getBaseContext(), tvLavPhone);
                break;
            default:
                break;
        }
    }

    @Override
    public void sendCodeSuccess() {
        ToastUtils.showShort("短信验证码发送成功请注意查收");
        tvLavSendCode.setClickable(false);
        mTimer = new CountDownTimer(60000L, 1000L) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (tvLavSendCode != null) {
                    tvLavSendCode.setText(millisUntilFinished / 1000 + "秒后重发");
                }
            }

            @Override
            public void onFinish() {
                tvLavSendCode.setClickable(true);
                tvLavSendCode.setText("重发验证码");
            }
        };
        mTimer.start();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }

    @Override
    public void logoutReasonResult() {
       ToastUtils.showLong("注销成功");
        MyApplication.getInstance().reLogin();
    }


    public class LogoutReasonView extends PopupWindow {
        public LogoutReasonView(Context mContext, View parent) {
            View view = View.inflate(mContext, R.layout.dialog_logout_reason, null);
            //view.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.filter));
            // LinearLayout ll_popup = (LinearLayout) view
            // .findViewById(R.id.ll_popup);
            // ll_popup.startAnimation(AnimationUtils.loadAnimation(mContext,
            // R.anim.push_bottom_in_1));

            setWidth(WindowManager.LayoutParams.FILL_PARENT);
            setHeight(WindowManager.LayoutParams.FILL_PARENT);
            setBackgroundDrawable(new BitmapDrawable());
            setFocusable(false);
            setOutsideTouchable(false);
            // 实例化一个ColorDrawable颜色为半透明
            ColorDrawable dw = new ColorDrawable(0xb0000000);
            // 设置SelectPicPopupWindow弹出窗体的背景
            this.setBackgroundDrawable(dw);
            setContentView(view);
            showAtLocation(parent, Gravity.BOTTOM, 0, 0);
            View view_logout_reason = view.findViewById(R.id.view_logout_reason);
            TextView tv_cancel = view.findViewById(R.id.tv_cancel);
            TextView tv_confirm = view.findViewById(R.id.tv_confirm);
            view_logout_reason.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });
            WheelView wv_logout_reason = view.findViewById(R.id.wv_logout_reason);
            List<String> mReasonItems = new ArrayList<>();
            mReasonItems.add("想要注册新账号");
            mReasonItems.add("这是多余账号");
            mReasonItems.add("个人原因（好友、CP影响）");
            mReasonItems.add("平台、游戏体验不好");
            mReasonItems.add("不好玩、不适合我");
            mReasonItems.add("这其他原因");
            wv_logout_reason.setAdapter(new ArrayWheelAdapter(mReasonItems));

            wv_logout_reason.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(int index) {

                }
            });
            wv_logout_reason.setCyclic(false);
            tv_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dismiss();
                }
            });
            tv_confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tvLavReason.setText(wv_logout_reason.getSelectedItemStr());
                    dismiss();
                }
            });
        }
    }
}