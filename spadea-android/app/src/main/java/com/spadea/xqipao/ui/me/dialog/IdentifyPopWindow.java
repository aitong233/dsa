package com.spadea.xqipao.ui.me.dialog;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.spadea.yuyin.R;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui.me.dialog
 * 创建人 王欧
 * 创建时间 2020/5/28 3:20 PM
 * 描述 describe
 */
public class IdentifyPopWindow extends PopupWindow {
    public IdentifyPopWindow(Context context) {
        super(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View rootView = inflater.inflate(R.layout.pop_identify, null);
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        setContentView(rootView);
        this.setBackgroundDrawable(new BitmapDrawable());
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.setTouchable(true);
    }

}
