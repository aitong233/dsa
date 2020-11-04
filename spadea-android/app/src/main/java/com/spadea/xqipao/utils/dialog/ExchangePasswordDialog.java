package com.spadea.xqipao.utils.dialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.spadea.yuyin.R;
import com.spadea.yuyin.util.utilcode.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class ExchangePasswordDialog extends BaseDialog {

    @BindView(R.id.ed_password)
    EditText edPassword;

    private OnExchangePasswordListener onExchangePasswordListener;

    public ExchangePasswordDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public int getLayout() {
        return R.layout.dialog_exchange_password;
    }

    @Override
    public void initView() {
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }

    @Override
    public  void initData() {

    }

    @OnClick({R.id.iv_close, R.id.tv_commit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
                dismiss();
                break;
            case R.id.tv_commit:
                String password = edPassword.getText().toString();
                if (TextUtils.isEmpty(password)) {
                    ToastUtils.showShort("请输入二级密码");
                    return;
                }
                if (onExchangePasswordListener != null) {
                    onExchangePasswordListener.onPasswword(password);
                }
                dismiss();
                break;
        }
    }

    public void setOnExchangePasswordListener(OnExchangePasswordListener onExchangePasswordListener) {
        this.onExchangePasswordListener = onExchangePasswordListener;
    }

    public interface OnExchangePasswordListener {
        void onPasswword(String password);
    }
}
