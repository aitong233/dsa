package com.qpyy.room.dialog;

import android.content.Context;
import android.support.annotation.NonNull;

import com.qpyy.libcommon.widget.dialog.BaseDialog;
import com.qpyy.room.R;
import com.qpyy.room.R2;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.dialog
 * 创建人 黄强
 * 创建时间 2020/8/11 10:58
 * 描述 describe
 */
public class ShopDialog extends BaseDialog {
    public ShopDialog(@NonNull Context context) {
        super(context);
    }

    public ShopDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    public int getLayout() {
        return R.layout.room_dialog_shop;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }
}
