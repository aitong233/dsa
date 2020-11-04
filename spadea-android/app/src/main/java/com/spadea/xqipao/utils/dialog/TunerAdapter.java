package com.spadea.xqipao.utils.dialog;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.spadea.yuyin.R;

public class TunerAdapter extends BaseQuickAdapter<TunerSheetDialog.Tuner, BaseViewHolder> {
    //  bg_tunre_no_select
    private int index = 0;

    public TunerAdapter() {
        super(R.layout.item_tunre);
    }

    @Override
    protected void convert(BaseViewHolder helper, TunerSheetDialog.Tuner item) {
        TextView tvName = helper.getView(R.id.tv_name);
        tvName.setText(item.getName());
        if (index == item.getType()) {
            tvName.setBackgroundResource(R.drawable.bg_tunre_select);
        } else {
            tvName.setBackgroundResource(R.drawable.bg_tunre_no_select);
        }
    }

    public void setIndex(int index) {
        this.index = index;
        notifyDataSetChanged();
    }

}
