package com.qpyy.module.me.adapter;

import android.graphics.Color;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qpyy.module.me.R;
import com.qpyy.module.me.bean.RoomTypeInfo;

public class RoomTypeAdapter extends BaseQuickAdapter<RoomTypeInfo, BaseViewHolder> {


    private int index = 0;


    public RoomTypeAdapter() {
        super(R.layout.me_item_room_type);
    }

    @Override
    protected void convert(BaseViewHolder helper, RoomTypeInfo item) {
        if (index == helper.getAdapterPosition()) {
            helper.setBackgroundRes(R.id.tv_text, R.drawable.bg_room_type_select);
            helper.setTextColor(R.id.tv_text, Color.parseColor("#6765FF"));
        } else {
            helper.setBackgroundRes(R.id.tv_text, R.drawable.bg_un_select);
            helper.setTextColor(R.id.tv_text, Color.parseColor("#9C9C9C"));
        }
        helper.setText(R.id.tv_text, item.getText());
    }


    public void setIndex(int x) {
        this.index = x;
        notifyDataSetChanged();
    }

    public RoomTypeInfo getSelect() {
        return getItem(index);
    }


}
