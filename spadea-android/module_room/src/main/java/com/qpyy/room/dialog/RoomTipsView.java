package com.qpyy.room.dialog;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.cpiz.android.bubbleview.BubbleFrameLayout;
import com.cpiz.android.bubbleview.BubblePopupWindow;
import com.cpiz.android.bubbleview.BubbleStyle;
import com.qpyy.room.R;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.dialog
 * 创建人 王欧
 * 创建时间 2020/8/4 5:18 PM
 * 描述 describe
 */
public class RoomTipsView {

    public static void show(Context context, View anchor, String title, String content) {
        BubbleFrameLayout bubbleView = (BubbleFrameLayout) LayoutInflater.from(context).inflate(R.layout.room_view_room_tips, null);
        TextView tvTitle = bubbleView.findViewById(R.id.tv_title);
        TextView tvContent = bubbleView.findViewById(R.id.tv_content);
        tvTitle.setText(title);
        tvContent.setText(content);
        bubbleView.setCornerRadius(ConvertUtils.dp2px(8));
        bubbleView.setFillColor(Color.WHITE);
        BubblePopupWindow window = new BubblePopupWindow(bubbleView, bubbleView);
        window.setCancelOnTouch(true);
        window.setCancelOnTouchOutside(true);
        window.showArrowTo(anchor, BubbleStyle.ArrowDirection.Up);

    }

    public static void showWithDelay(Context context, View anchor, String title, String content) {
        BubbleFrameLayout bubbleView = (BubbleFrameLayout) LayoutInflater.from(context).inflate(R.layout.room_view_room_tips, null);
        TextView tvTitle = bubbleView.findViewById(R.id.tv_title);
        TextView tvContent = bubbleView.findViewById(R.id.tv_content);
        tvTitle.setText(title);
        tvContent.setText(content);
        bubbleView.setCornerRadius(ConvertUtils.dp2px(8));
        bubbleView.setFillColor(Color.WHITE);
        BubblePopupWindow window = new BubblePopupWindow(bubbleView, bubbleView);
        window.setCancelOnTouch(true);
        window.setCancelOnTouchOutside(true);
        window.showArrowTo(anchor, BubbleStyle.ArrowDirection.Up);
        anchor.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (window!=null&&window.isShowing()){
                    window.dismiss();
                }
            }
        },2000);
    }
}
