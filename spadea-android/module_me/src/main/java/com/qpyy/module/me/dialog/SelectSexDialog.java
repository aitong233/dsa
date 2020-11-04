package com.qpyy.module.me.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.qpyy.libcommon.widget.dialog.BaseBottomSheetDialog;
import com.qpyy.module.me.R;
import com.qpyy.module.me.R2;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import top.defaults.view.PickerView;

public class SelectSexDialog<T extends PickerView.PickerItem> extends BaseBottomSheetDialog {


    @BindView(R2.id.pickerView)
    PickerView pickerView;
    @BindView(R2.id.tv_cancel)
    TextView tvCancel;
    @BindView(R2.id.tv_confirm)
    TextView tvConfirm;
    private OnSelectSexClickListener mOnSelectSexClickListener;

    public SelectSexDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public int getLayout() {
        return R.layout.me_dialog_select_sex;
    }

    @Override
    public void initView() {
        this.setCancelable(false);
        this.setCanceledOnTouchOutside(false);
    }

    @Override
    public void initData() {

    }


    @OnClick({R2.id.tv_cancel, R2.id.tv_confirm})
    public void onClick(View view) {
        dismiss();
        int id = view.getId();
        if (id == R.id.tv_cancel) {
        } else if (id == R.id.tv_confirm) {
            if (mOnSelectSexClickListener != null) {
                mOnSelectSexClickListener.onConfirmClick(pickerView.getSelectedItemPosition());
            }
        }
    }

    public void addOnSelectSexClickListener(OnSelectSexClickListener onSelectSexClickListener) {
        this.mOnSelectSexClickListener = onSelectSexClickListener;
    }

    public void setData(List<T> data) {
        pickerView.setItems(data, new PickerView.OnItemSelectedListener<T>() {
            @Override
            public void onItemSelected(T item) {

            }
        });
    }

    public interface OnSelectSexClickListener {
        void onConfirmClick(int postion);
    }

}
