package com.qpyy.libcommon.widget.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.qpyy.libcommon.R;
import com.qpyy.libcommon.R2;
import com.qpyy.libcommon.base.BaseDialog;

import butterknife.BindView;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.libcommon.widget.dialog
 * 创建人 王欧
 * 创建时间 2020/6/24 3:37 PM
 * 描述 describe
 */
public class LoadingDialog extends BaseDialog {


    TextView tipTextView;

    public LoadingDialog(@NonNull Context context) {
        super(context, R.style.LoadingDialog);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_loading;
    }

    @Override
    public void initView() {
        tipTextView = findViewById(R.id.tipTextView);
    }

    @Override
    public void initData() {

    }

    public void setContent(String content) {
        tipTextView.setText(content);
    }
}
