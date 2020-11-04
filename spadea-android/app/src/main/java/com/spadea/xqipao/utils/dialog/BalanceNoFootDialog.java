package com.spadea.xqipao.utils.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.spadea.yuyin.R;

import butterknife.OnClick;

public class BalanceNoFootDialog extends BaseDialog {


    private BalanceNoFootOnClickListener mBalanceNoFootOnClickListener;

    public BalanceNoFootDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public int getLayout() {
        return R.layout.dialog_balance_no_foot;
    }

    @Override
    public void initView() {
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.rl_recharge,R.id.rl_clean})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_recharge:
                if (mBalanceNoFootOnClickListener != null) {
                    mBalanceNoFootOnClickListener.toRecharge();
                }
                break;
        }
        this.dismiss();
    }

    public void setBalanceNoFootOnClickListener(BalanceNoFootOnClickListener balanceNoFootOnClickListener) {
        this.mBalanceNoFootOnClickListener = balanceNoFootOnClickListener;
    }

    public interface BalanceNoFootOnClickListener {
        void toRecharge();
    }
}
