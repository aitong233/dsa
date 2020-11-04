package com.spadea.xqipao.ui.me.adapter;


import com.chad.library.adapter.base.BaseViewHolder;
import com.spadea.yuyin.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.spadea.xqipao.data.CountryBean;


public class PickCityAdapter extends BaseQuickAdapter<CountryBean, BaseViewHolder> {




    public PickCityAdapter() {
        super(R.layout.item_city);
    }

    @Override
    protected void convert(BaseViewHolder helper, final CountryBean item) {
        helper.setText(R.id.tv_city_name, item.getName()).setText(R.id.tv_city_code, item.getCode());
    }




}