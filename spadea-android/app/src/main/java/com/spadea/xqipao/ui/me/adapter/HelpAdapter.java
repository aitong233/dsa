package com.spadea.xqipao.ui.me.adapter;

import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.spadea.xqipao.data.HelpModel;
import com.spadea.yuyin.R;

public class HelpAdapter extends BaseQuickAdapter<HelpModel, BaseViewHolder> {


    private int index = 0;


    public HelpAdapter() {
        super(R.layout.item_help);
    }

    @Override
    protected void convert(BaseViewHolder helper, HelpModel item) {
        helper.setText(R.id.tv_title, item.getTitle())
                .setText(R.id.tv_content, Html.fromHtml(item.getContent()));
        TextView tvContent = helper.getView(R.id.tv_content);
        if (helper.getAdapterPosition() == index) {
            tvContent.setVisibility(View.VISIBLE);
        } else {
            tvContent.setVisibility(View.GONE);
        }
    }

    public void setIndex(int index) {
        this.index = index;
        notifyDataSetChanged();
    }
}
