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

public class UserDetailsMoreDialog extends BaseBottomSheetDialog {


    @BindView(R2.id.tv_lh)
    TextView tvLh;
    @BindView(R2.id.tv_jb)
    TextView tvJb;
    @BindView(R2.id.tv_cancel)
    TextView tvCancel;

    private OnUserDetailsMoreListener mOnUserDetailsMoreListener;

    public UserDetailsMoreDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public int getLayout() {
        return R.layout.me_dialog_user_details_more;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }


    @OnClick({R2.id.tv_lh, R2.id.tv_jb, R2.id.tv_cancel})
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.tv_lh) {
            if (mOnUserDetailsMoreListener != null) {
                mOnUserDetailsMoreListener.onBlock();
            }
        } else if (id == R.id.tv_jb) {
            if (mOnUserDetailsMoreListener != null) {
                mOnUserDetailsMoreListener.onReport();
            }
        } else if (id == R.id.tv_cancel) {
        }
        dismiss();
    }


    public void addOnUserDetailsMoreListener(OnUserDetailsMoreListener onUserDetailsMoreListener) {
        mOnUserDetailsMoreListener = onUserDetailsMoreListener;
    }

    public interface OnUserDetailsMoreListener {
        void onBlock();

        void onReport();
    }
}
