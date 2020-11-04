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
import com.spadea.xqipao.utils.popupwindow.adapter.SelectObjectAdapter;
import com.spadea.xqipao.data.GiftUserBean;
import com.spadea.yuyin.R;

import java.util.List;

public class SelectObjectPopupwindo extends PopupWindow {

    private View rootView;
    private SelectObjectAdapter selectObjectAdapter;

    public SelectObjectPopupwindo(Context context, BaseQuickAdapter.OnItemClickListener onItemClickListener) {
        LayoutInflater inflater = LayoutInflater.from(context);
        rootView = inflater.inflate(R.layout.pop_gift_left, null);
        RecyclerView recyclerView = rootView.findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(selectObjectAdapter = new SelectObjectAdapter());
        setContentView(rootView);
        this.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setBackgroundDrawable(new BitmapDrawable());
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.setTouchable(true);
        this.setAnimationStyle(R.style.mypopwindow_anim_style);
        selectObjectAdapter.setOnItemClickListener(onItemClickListener);
    }


    public void setNewData(List<GiftUserBean> data) {
        if (selectObjectAdapter != null) {
            selectObjectAdapter.setNewData(data);
        }
    }


}
