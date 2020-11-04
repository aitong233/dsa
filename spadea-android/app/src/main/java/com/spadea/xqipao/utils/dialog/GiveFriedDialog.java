package com.spadea.xqipao.utils.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.spadea.yuyin.R;

import butterknife.BindView;
import butterknife.OnClick;

public class GiveFriedDialog extends BaseDialog {

    @BindView(R.id.tv_tips)
    TextView tvTips;
    @BindView(R.id.rl_clean)
    RelativeLayout rlClean;
    @BindView(R.id.rl_recharge)
    RelativeLayout rlRecharge;


    private GiveFriedOnClickListener mGiveFriedOnClickListener;

    public GiveFriedDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public int getLayout() {
        return R.layout.dialog_give_fried;
    }

    @Override
    public void initView() {
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }

    @Override
    public void initData() {

    }

    public void setText(String text) {
        tvTips.setText(text);
    }

    @OnClick({R.id.rl_clean, R.id.rl_recharge})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_clean:
                dismiss();
                break;
            case R.id.rl_recharge:
                if (mGiveFriedOnClickListener != null) {
                    mGiveFriedOnClickListener.give();
                }
                break;
        }

    }

    public void setmGiveFriedOnClickListener(GiveFriedOnClickListener mGiveFriedOnClickListener) {
        this.mGiveFriedOnClickListener = mGiveFriedOnClickListener;
    }

    public interface GiveFriedOnClickListener {
        void give();
    }

}
