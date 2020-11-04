package com.spadea.xqipao.utils.dialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.spadea.yuyin.R;
import com.spadea.yuyin.util.utilcode.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class ExchangeDialog extends BaseDialog {


    @BindView(R.id.ed_num)
    EditText edNum;
    @BindView(R.id.tv_exchange)
    TextView tvExchange;
    @BindView(R.id.rl)
    RelativeLayout rl;
    @BindView(R.id.tv_balance)
    TextView mTvBalance;
    @BindView(R.id.tag)
    TextView tag;

    private String balance;

    private OnExchangeListener onExchangeListener;

    public ExchangeDialog(@NonNull Context context) {
        super(context);
    }

    public ExchangeDialog(@NonNull Context context, String t) {
        super(context);
        tag.setText(t);
    }


    @Override
    public int getLayout() {
        return R.layout.dialog_exchange;
    }

    @Override
    public void initView() {
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }

    @Override
    public void initData() {

    }

    public void setBalance(String balance,String t) {
        this.balance = balance;
        mTvBalance.setText(balance);
        edNum.setText("");
        tag.setText(t);
    }

    @OnClick({R.id.tv_exchange, R.id.rl, R.id.tv_all})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_exchange:
                String num = edNum.getText().toString();
                if (TextUtils.isEmpty(num)) {
                    ToastUtils.showShort("请输入兑换数量");
                    return;
                }
                if (onExchangeListener != null) {
                    onExchangeListener.onInputNum(num);
                }
                break;
            case R.id.rl:
                this.dismiss();
                break;
            case R.id.tv_all:
                try {
                    edNum.setText(String.valueOf((int)Double.parseDouble(balance)));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
        }
    }

    public void setOnExchangeListener(OnExchangeListener onExchangeListener) {
        this.onExchangeListener = onExchangeListener;
    }

    public interface OnExchangeListener {
        void onInputNum(String num);
    }
}
