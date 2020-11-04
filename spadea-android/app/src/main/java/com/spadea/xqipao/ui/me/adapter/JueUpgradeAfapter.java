package com.spadea.xqipao.ui.me.adapter;

import android.graphics.Color;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.spadea.xqipao.data.NobilityModel;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.ImageLoader;

public class JueUpgradeAfapter extends BaseQuickAdapter<NobilityModel, BaseViewHolder> {


    private int index = -1;


    public JueUpgradeAfapter() {
        super(R.layout.item_jue_upgrade);
    }

    @Override
    protected void convert(BaseViewHolder helper, NobilityModel item) {
        ImageLoader.loadImage(mContext, helper.getView(R.id.iv_img), item.getHead_picture());
        helper.setText(R.id.tv_name, "升级至" + item.getName())
                .setText(R.id.tv_price, item.getPrice() + "金币");
        if (helper.getAdapterPosition() == index) {
            helper.setTextColor(R.id.tv_name, Color.parseColor("#FF8890")).setTextColor(R.id.tv_price, Color.parseColor("#FF8890"));
            helper.setBackgroundRes(R.id.ll_item, R.mipmap.bg_jue_select);
        } else {
            helper.setTextColor(R.id.tv_name, Color.parseColor("#333333")).setTextColor(R.id.tv_price, Color.parseColor("#333333"));
            helper.setBackgroundRes(R.id.ll_item, R.mipmap.bg_jue_un_select);
        }
    }


    public void setIndex(int index) {
        this.index = index;
        notifyDataSetChanged();
    }
}
