package com.spadea.xqipao.widget.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.spadea.yuyin.R;
import com.spadea.yuyin.util.utilcode.ScreenUtils;
import com.spadea.xqipao.utils.dialog.BaseDialog;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.widget.dialog
 * 创建人 王欧
 * 创建时间 2020/6/2 1:00 PM
 * 描述 describe
 */
public class CustomAlertDialog extends BaseDialog {
    @BindView(R.id.tv_msg)
    TextView mTvMsg;
    @BindView(R.id.tv_left)
    TextView mTvLeft;
    @BindView(R.id.tv_right)
    TextView mTvRight;
    private String msg;
    private String leftText;
    private String rightText;
    private CustomDialogListener mListener;

    public CustomAlertDialog(@NonNull Context context, String msg, String leftText, String rightText, CustomDialogListener listener) {
        super(context);
        this.msg = msg;
        this.leftText = leftText;
        this.rightText = rightText;
        mListener = listener;
        setData();
    }

    private void setData() {
        mTvMsg.setText(msg);
        if (!TextUtils.isEmpty(leftText)) {
            mTvLeft.setText(leftText);
        }
        if (!TextUtils.isEmpty(rightText)) {
            mTvRight.setText(rightText);
        }
    }

    @Override
    public int getLayout() {
        return R.layout.dialog_custom_alert;
    }

    @Override
    public void initView() {
        getWindow().setLayout((int) (ScreenUtils.getScreenWidth()*283.0/375), WindowManager.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.tv_left, R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_left:
                dismiss();
                mListener.onCancel();
                break;
            case R.id.tv_right:
                dismiss();
                mListener.onConfirm();
                break;
        }
    }


    public interface CustomDialogListener {
        void onConfirm();

        void onCancel();
    }

}
