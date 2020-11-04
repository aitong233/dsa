package com.qpyy.libcommon.widget.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;


import com.qpyy.libcommon.R;

public class CommonDialog extends BaseDialog {

    TextView tvContent;
    TextView textViewLeft;
    TextView textViewRight;

    private OnClickListener mOnClickListener;


    public CommonDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public int getLayout() {
        return R.layout.dialog_common;
    }

    @Override
    public void initView() {
        tvContent = dialogView.findViewById(R.id.tv_content);
        textViewLeft = dialogView.findViewById(R.id.tv_left);
        textViewRight = dialogView.findViewById(R.id.tv_right);
        textViewLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                mOnClickListener.onLeftClick();
            }
        });
        textViewRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                mOnClickListener.onRightClick();
            }
        });
    }

    @Override
    public void initData() {

    }


    public void setContent(String content) {
        tvContent.setText(content);
    }

    public void setLeftText(String leftText) {
        textViewLeft.setText(leftText);
    }

    public void setRightText(String rightText) {
        textViewRight.setText(rightText);
    }

    public void setmOnClickListener(OnClickListener onClickListener) {
        this.mOnClickListener = onClickListener;
    }


    public interface OnClickListener {
        void onLeftClick();

        void onRightClick();
    }
}
