package com.spadea.xqipao.utils.dialog.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.spadea.xqipao.data.WinJackpotModel;
import com.spadea.yuyin.MyApplication;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.ImageLoader;
import com.makeramen.roundedimageview.RoundedImageView;


public class CatFishingJakpotAdapter extends BaseQuickAdapter<WinJackpotModel, BaseViewHolder> {


    public CatFishingJakpotAdapter() {
        super(R.layout.item_cat_fishing_jackpot);
    }

    @Override
    protected void convert(BaseViewHolder helper, WinJackpotModel item) {
        helper.setText(R.id.tv, String.valueOf(helper.getAdapterPosition() + 1))
                .setText(R.id.tv_name, item.getName())
                .setText(R.id.tv_num, item.getPrice() + " 金币");
        RoundedImageView roundedImageView = helper.getView(R.id.riv);
        ImageLoader.loadHead(MyApplication.getInstance(), roundedImageView, item.getPicture());
    }
}
