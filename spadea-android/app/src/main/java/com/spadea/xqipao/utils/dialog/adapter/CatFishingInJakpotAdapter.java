package com.spadea.xqipao.utils.dialog.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.spadea.xqipao.data.EggGiftModel;
import com.spadea.yuyin.MyApplication;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.ImageLoader;
import com.makeramen.roundedimageview.RoundedImageView;


public class CatFishingInJakpotAdapter extends BaseQuickAdapter<EggGiftModel, BaseViewHolder> {


    public CatFishingInJakpotAdapter() {
        super(R.layout.item_cat_fishing_in_jackpot);
    }

    @Override
    protected void convert(BaseViewHolder helper, EggGiftModel item) {
        helper.setText(R.id.tv, String.valueOf(helper.getAdapterPosition() + 1))
                .setText(R.id.tv_name, item.getPrize_title())
                .setText(R.id.tv_num, item.getPrice() + " 金币")
        .setText(R.id.tv_nums,"x "+item.getNumber())
        ;
        RoundedImageView roundedImageView = helper.getView(R.id.riv);
        ImageLoader.loadHead(MyApplication.getInstance(), roundedImageView, item.getPicture());
    }
}
