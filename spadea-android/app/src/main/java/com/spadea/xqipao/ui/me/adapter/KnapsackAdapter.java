package com.spadea.xqipao.ui.me.adapter;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.spadea.xqipao.data.MyProductsModel;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.ImageLoader;

public class KnapsackAdapter extends BaseQuickAdapter<MyProductsModel, BaseViewHolder> {

    public KnapsackAdapter() {
        super(R.layout.item_knapsack);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyProductsModel item) {

        ImageLoader.loadImage(mContext, helper.getView(R.id.iv_img), item.getPicture());
        helper.setText(R.id.tv_purchase_time, "购买日期：" + item.getAdd_time())
                .setText(R.id.tv_name, item.getTitle())
                .setText(R.id.tv_time, item.getLeft_time());

        ImageView iv_un_use = helper.getView(R.id.iv_un_use);
        ImageView iv_use = helper.getView(R.id.iv_use);
        ImageView iv_renew = helper.getView(R.id.iv_renew);
        ImageView iv_be_overdue = helper.getView(R.id.iv_be_overdue);
        iv_un_use.setVisibility(View.GONE);
        iv_use.setVisibility(View.GONE);
        iv_renew.setVisibility(View.GONE);
        iv_be_overdue.setVisibility(View.GONE);

        if (item.getState().equals("2")) {
            iv_be_overdue.setVisibility(View.VISIBLE);
        } else if (item.getState().equals("0")) {
            iv_un_use.setVisibility(View.VISIBLE);
            iv_renew.setVisibility(View.VISIBLE);
        } else {
            iv_use.setVisibility(View.VISIBLE);
            iv_renew.setVisibility(View.VISIBLE);
        }

        helper.addOnClickListener(R.id.iv_renew);
        helper.addOnClickListener(R.id.iv_un_use);
        helper.addOnClickListener(R.id.iv_use);
    }
}
