package com.qpyy.module.me.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.qpyy.libcommon.widget.dialog.BaseBottomSheetDialog;
import com.qpyy.module.me.R;
import com.qpyy.module.me.R2;

import butterknife.BindView;
import butterknife.OnClick;

public class SaveImgDialog extends BaseBottomSheetDialog {


    @BindView(R2.id.tv_save)
    TextView tvSave;
    @BindView(R2.id.tv_cancel)
    TextView tvCancel;

    private OnSaveImgListener mOnSaveImgListener;

    public SaveImgDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public int getLayout() {
        return R.layout.me_dialog_save_img;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @OnClick({R2.id.tv_save, R2.id.tv_cancel})
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.tv_save) {
            if (mOnSaveImgListener != null) {
                mOnSaveImgListener.onStartSaveImg();
            }
        }
        dismiss();
    }

    public void addOnSaveImgListener(OnSaveImgListener onSaveImgListener) {
        this.mOnSaveImgListener = onSaveImgListener;
    }

    public interface OnSaveImgListener {
        void onStartSaveImg();
    }
}
