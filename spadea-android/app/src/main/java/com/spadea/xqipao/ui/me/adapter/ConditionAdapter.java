package com.spadea.xqipao.ui.me.adapter;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.spadea.xqipao.data.CashTypeModel;
import com.spadea.yuyin.R;

public class ConditionAdapter extends BaseQuickAdapter<CashTypeModel, BaseViewHolder> {


    private int indewx = 0;

    public ConditionAdapter() {
        super(R.layout.item_condition);
    }

    @Override
    protected void convert(BaseViewHolder helper, CashTypeModel item) {
        TextView tvName = helper.getView(R.id.tv_name);
        tvName.setText(item.getName());
        if (helper.getAdapterPosition() == indewx) {
            tvName.setBackgroundResource(R.drawable.icon_condition_select);
        } else {
            tvName.setBackgroundResource(R.drawable.bg_item_condition);
        }
    }

    public void setIndewx(int indewx) {
        this.indewx = indewx;
        notifyDataSetChanged();
    }

    public int getType() {
        try {
            int type = getData().get(indewx).getId();
            return type;
        } catch (Exception e) {
            return 0;
        }
    }
}
