package com.spadea.xqipao.ui.me.adapter;

import android.graphics.Color;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.spadea.xqipao.data.NobilityInfo;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.ImageLoader;

public class JueRenewAdapter extends BaseQuickAdapter<NobilityInfo.RenewBean, BaseViewHolder> {


    private int index = -1;

    private String nobilityPicture;

    public JueRenewAdapter() {
        super(R.layout.item_jur_renew);
    }

    @Override
    protected void convert(BaseViewHolder helper, NobilityInfo.RenewBean item) {
        ImageLoader.loadImage(helper.getView(R.id.iv_img),nobilityPicture);
        helper.setText(R.id.tv_day, item.getDay() + "天").setText(R.id.tv_price, item.getPrice() + "金币");
        if (helper.getAdapterPosition() == index) {
            helper.setBackgroundRes(R.id.ll_item, R.mipmap.bg_jue_select);
            helper.setTextColor(R.id.tv_day, Color.parseColor("#FFFF8890")).setTextColor(R.id.tv_price, Color.parseColor("#FFFF8890"));
        } else {
            helper.setBackgroundRes(R.id.ll_item, R.mipmap.bg_jue_un_select);
            helper.setTextColor(R.id.tv_day, Color.parseColor("#FF333333")).setTextColor(R.id.tv_price, Color.parseColor("#FF333333"));
        }
    }


    public void setIndex(int index) {
        this.index = index;
        notifyDataSetChanged();
    }

    public String getNobilityPicture() {
        return nobilityPicture;
    }

    public void setNobilityPicture(String nobilityPicture) {
        this.nobilityPicture = nobilityPicture;
    }
}
