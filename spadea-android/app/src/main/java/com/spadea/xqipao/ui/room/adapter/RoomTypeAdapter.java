package com.spadea.xqipao.ui.room.adapter;

import android.graphics.Color;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.spadea.xqipao.data.RoomExtraModel;
import com.spadea.yuyin.R;

public class RoomTypeAdapter extends BaseQuickAdapter<RoomExtraModel.TypeBean, BaseViewHolder> {


    private String typeId = "";


    public RoomTypeAdapter() {
        super(R.layout.item_room_pattern);
    }


    /**
     * bg_room_patter_un_select
     *
     * @param helper
     * @param item
     */
    @Override
    protected void convert(BaseViewHolder helper, RoomExtraModel.TypeBean item) {
        TextView tvPaaterName = helper.getView(R.id.tv_patter_name);
        if (item.getId().equals(typeId)) {
            tvPaaterName.setTextColor(Color.parseColor("#FFFFFF"));
            tvPaaterName.setBackgroundResource(R.drawable.bg_room_pattern_select);
        } else {
            tvPaaterName.setTextColor(Color.parseColor("#000000"));
            tvPaaterName.setBackgroundResource(R.drawable.bg_room_patter_un_select);
        }

        tvPaaterName.setText(item.getType_name());
    }



    public void setTypeId(String typeId) {
        this.typeId = typeId;
        notifyDataSetChanged();
    }
}

