package com.spadea.xqipao.ui.me.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.spadea.xqipao.data.ProductsModel;
import com.spadea.yuyin.MyApplication;
import com.spadea.yuyin.R;
 import com.spadea.yuyin.util.ImageLoader;
import com.qpyy.libcommon.bean.UserBean;

import java.util.List;

public class ShopAdapter extends BaseQuickAdapter<ProductsModel, BaseViewHolder> {


    private String id = "0";

    public ShopAdapter(String id) {
        super(R.layout.item_shop);
        this.id = id;
    }

    @Override
    protected void convert(BaseViewHolder helper, ProductsModel item) {
        helper.setText(R.id.tv_name, item.getTitle())
                .setText(R.id.tv_brief, getDay(item.getPrices()));

        if (item.getPrices() != null && item.getPrices().size() != 0) {
            helper.setVisible(R.id.tv_price, true)
                    .setText(R.id.tv_price, item.getPrices().get(0).getPrice() + "金币");
        } else {
            helper.setVisible(R.id.tv_price, false);
        }

        if (id.equals("1")) {
            helper.setVisible(R.id.rl_pendant, true).setVisible(R.id.iv_img, false);
            UserBean user = MyApplication.getInstance().getUser();
            if (user != null) {
                helper.setVisible(R.id.riv, true);
                ImageLoader.loadHead(mContext, helper.getView(R.id.riv), user.getHead_picture());
            } else {
                helper.setVisible(R.id.riv, false);
            }
            ImageLoader.loadImage(mContext, helper.getView(R.id.iv_frame), item.getPicture());
        } else {
            helper.setVisible(R.id.rl_pendant, false).setVisible(R.id.iv_img, true);
            ImageLoader.loadImage(mContext, helper.getView(R.id.iv_img), item.getPicture());
        }

        helper.addOnClickListener(R.id.tv_purchase);
    }


    private String getDay(List<ProductsModel.PricesBean> prices) {
        String day = "";
        for (ProductsModel.PricesBean pricesBean : prices) {
            day += pricesBean.getDay() + "天;";
        }
        return day;
    }


}
