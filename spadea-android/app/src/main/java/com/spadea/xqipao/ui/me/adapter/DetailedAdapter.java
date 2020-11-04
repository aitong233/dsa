package com.spadea.xqipao.ui.me.adapter;

import android.graphics.Color;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.spadea.xqipao.data.EarningsModel;
import com.spadea.yuyin.R;

public class DetailedAdapter extends BaseQuickAdapter<EarningsModel.EarningInfo, BaseViewHolder> {

    public DetailedAdapter() {
        super(R.layout.item_detaile);
    }

    @Override
    protected void convert(BaseViewHolder helper, EarningsModel.EarningInfo item) {
        helper.setText(R.id.tv_time, item.getChange_time())
                .setText(R.id.tv_name, item.getContent());
        TextView tvType = helper.getView(R.id.tv_type);
        TextView tvNum = helper.getView(R.id.tv_num);
        if (item.getSymbol()==1){
            tvNum.setText("+"+item.getUser_money());
            tvType.setText("收入");
            tvNum.setTextColor(Color.parseColor("#FF8890"));
        }else {
            tvNum.setText("-"+item.getUser_money());
            tvType.setText("支出");
            tvNum.setTextColor(Color.parseColor("#000000"));
        }
    }
}
