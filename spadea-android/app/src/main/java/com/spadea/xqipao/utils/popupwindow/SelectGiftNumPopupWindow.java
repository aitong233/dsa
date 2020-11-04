package com.spadea.xqipao.utils.popupwindow;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.spadea.xqipao.utils.popupwindow.adapter.SelectGiftNumAdapter;
import com.spadea.xqipao.data.GiftNumBean;
import com.spadea.yuyin.R;

import java.util.List;

public class SelectGiftNumPopupWindow extends PopupWindow {
    private View rootView;
    private SelectGiftNumAdapter selectGiftNumAdapter;

    public SelectGiftNumPopupWindow(Context context,   BaseQuickAdapter.OnItemClickListener onItemClickListener) {
        LayoutInflater inflater = LayoutInflater.from(context);
        rootView = inflater.inflate(R.layout.pop_gift_right, null);
        RecyclerView recyclerView = rootView.findViewById(R.id.rv);
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
