package com.spadea.xqipao.utils.dialog.adapter;

import android.graphics.Color;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.spadea.xqipao.data.ProductsModel;
import com.spadea.yuyin.R;

import java.util.List;

public class ShopDayAdapter extends BaseQuickAdapter<ProductsModel.PricesBean, BaseViewHolder> {

    private int index = 0;

    public ShopDayAdapter() {
        super(R.layout.item_shop_day);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProductsModel.PricesBean item) {
        helper.setText(R.id.tv_day, item.getDay() + "天");
        if (helper.getAdapterPosition() == index) {
            helper.setTextColor(R.id.tv_day, Color.parseColor("#FF8890")).setBackgroundRes(R.id.tv_day, R.mipmap.bg_shop_select);
        } else {
            helper.setTextColor(R.id.tv_day, Color.parseColor("#999999")).setBackgroundRes(R.id.tv_day, R.mipmap.bg_shop_un_select);
        }
    }

    public void setIndex(int index) {
        this.index = index;
        notifyDataSetChanged();
    }


    public String getPriceId() {
        List<ProductsModel.PricesBean> data = getData();
        return data.get(index).getId();
    }

    public ProductsModel.PricesBean getPrice() {
        List<ProductsModel.PricesBean> data = getData();
        return data.get(index);
    }


}
