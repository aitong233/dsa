package com.spadea.xqipao.utils.dialog.room;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.spadea.xqipao.utils.dialog.BaseDialog;
import com.spadea.yuyin.R;

import butterknife.BindView;

public class RoomTakeDialog extends BaseDialog {

    @BindView(R.id.tv_text)
    TextView tvText;

    public RoomTakeDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public int getLayout() {
        return R.layout.dialog_room_take;
    }

    @Override
    public void initView() {
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
    }

    @Override
    public void initData() {

    }


    public void setData(String text) {
        tvText.setText(text);
    }
}
