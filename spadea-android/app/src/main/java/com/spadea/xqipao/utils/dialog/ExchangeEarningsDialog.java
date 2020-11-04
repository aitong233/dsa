package com.spadea.xqipao.utils.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.spadea.yuyin.R;
import com.spadea.yuyin.util.utilcode.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 兑换收益弹窗
 */
public class ExchangeEarningsDialog extends BaseDialog {

    @BindView(R.id.et_add)
    EditText etAdd;
    @BindView(R.id.et_password)
    EditText etPassword;


    private ExchangeEarningsClickListener mExchangeEarningsClickListener;

    public ExchangeEarningsDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public int getLayout() {
        return R.layout.dialog_exchange_earnings;
    }

    @Override
    public  void initView() {

    }

    @Override
    public  void initData() {

    }


    @OnClick({R.id.tv_cancel, R.id.tv_sure})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                dismiss();
                break;
            case R.id.tv_sure:
                String num = etAdd.getText().toString();
                String password = etPassword.getText().toString();
                if (TextUtils.isEmpty(num) && Double.valueOf(num) > 0) {
                    ToastUtils.showShort("请输入正确的兑换数量");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    ToastUtils.showShort("请输入二级密码");
                    return;
                }
                if (mExchangeEarningsClickListener != null) {
                    mExchangeEarningsClickListener.confirm(num, password);
                }
                break;
        }
    }


    public void addExchangeEarningsClickListener(ExchangeEarningsClickListener exchangeEarningsClickListener) {
        this.mExchangeEarningsClickListener = exchangeEarningsClickListener;
    }


    public interface ExchangeEarningsClickListener {
        void confirm(String num, String password);
    }


}
