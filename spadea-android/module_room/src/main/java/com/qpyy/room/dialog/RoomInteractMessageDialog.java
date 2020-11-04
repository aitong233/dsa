package com.qpyy.room.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.qpyy.libcommon.widget.dialog.BaseDialog;
import com.qpyy.room.R;
import com.qpyy.room.R2;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.dialog
 * 创建人 黄强
 * 创建时间 2020/8/7 14:52
 * 描述 describe
 */
public class RoomInteractMessageDialog extends BaseDialog {


    private static final String TAG = "RoomInteractMessage";

    public RoomInteractMessageDialog(@NonNull Context context) {
        super(context);
        Log.d(TAG, "(Start)启动了===========================RoomInteractMessageDialog");
    }

    @Override
    public int getLayout() {
        return R.layout.room_dialog_interact_message_list;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }
}
