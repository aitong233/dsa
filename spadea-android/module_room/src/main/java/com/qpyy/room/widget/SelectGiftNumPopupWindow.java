package com.qpyy.room.widget;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qpyy.room.R;
import com.qpyy.room.R2;
import com.qpyy.room.adapter.SelectGiftNumAdapter;
import com.qpyy.room.bean.GiftNumBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.widget
 * 创建人 黄强
 * 创建时间 2020/8/6 14:10
 * 描述 选择礼物数量窗口
 */
public class SelectGiftNumPopupWindow extends PopupWindow {
    private View rootView;
    private SelectGiftNumAdapter selectGiftNumAdapter;

    public SelectGiftNumPopupWindow(Context context, BaseQuickAdapter.OnItemClickListener onItemClickListener) {
        LayoutInflater inflater = LayoutInflater.from(context);
        rootView = inflater.inflate(R.layout.room_window_pop_gift, null);
        RecyclerView recyclerView = rootView.findViewById(R.id.rv_gift_num_window);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(selectGiftNumAdapter = new SelectGiftNumAdapter());

        selectGiftNumAdapter.setOnItemClickListener(onItemClickListener);
        setContentView(rootView);
        this.setBackgroundDrawable(new BitmapDrawable());
        this.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.setTouchable(true);
        this.setAnimationStyle(R.style.mypopwindow_anim_style);
    }


    public void setData(List<GiftNumBean> data) {
        if (selectGiftNumAdapter != null) {
            selectGiftNumAdapter.setNewData(data);
        }
    }

}
