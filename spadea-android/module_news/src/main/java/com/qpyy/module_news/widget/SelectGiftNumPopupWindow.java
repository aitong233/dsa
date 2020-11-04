package com.qpyy.module_news.widget;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qpyy.module_news.R;
import com.qpyy.module_news.bean.GiftNumBean;

import java.util.List;

public class SelectGiftNumPopupWindow extends PopupWindow {
    private SelectGiftNumAdapter selectGiftNumAdapter;

    public SelectGiftNumPopupWindow(Context context, BaseQuickAdapter.OnItemClickListener onItemClickListener) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View rootView = inflater.inflate(R.layout.news_pop_gift_num, null);
        RecyclerView recyclerView = rootView.findViewById(R.id.recycle_view);
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


    private class SelectGiftNumAdapter extends BaseQuickAdapter<GiftNumBean, BaseViewHolder> {
        public SelectGiftNumAdapter() {
            super(R.layout.news_new_rv_item_gift_pop_num);
        }

        @Override
        protected void convert(BaseViewHolder helper, GiftNumBean item) {
            helper.setText(R.id.tv0, item.getNumber());
            helper.setText(R.id.tv1, item.getText());
        }
    }
}
