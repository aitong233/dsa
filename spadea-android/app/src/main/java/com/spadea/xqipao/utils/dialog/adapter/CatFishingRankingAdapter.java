package com.spadea.xqipao.utils.dialog.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.spadea.xqipao.data.CatFishingModel;
import com.spadea.yuyin.R;


public class CatFishingRankingAdapter extends BaseQuickAdapter<CatFishingModel, BaseViewHolder> {


    public CatFishingRankingAdapter() {
        super(R.layout.item_cat_fishing_ranking);
    }

    @Override
    protected void convert(BaseViewHolder helper, CatFishingModel item) {
//        RoundedImageView roundedImageView = helper.getView(R.id.riv);
//        ImageLoader.loadHead(MyApplication.getInstance(), roundedImageView, item.getHead_picture());
//
//        helper.setText(R.id.tv_name, item.getNickname())
//                .setText(R.id.tv_num, item.getNumber() + " 金币");
//
//        TextView tv = helper.getView(R.id.tv);
//        ImageView iv = helper.getView(R.id.iv);
//        tv.setVisibility(View.GONE);
//        iv.setVisibility(View.GONE);
//        switch (helper.getAdapterPosition()) {
//            case 0:
//                iv.setVisibility(View.VISIBLE);
//                iv.setBackgroundResource(R.drawable.icon_cat_ranking1);
//                break;
//            case 1:
//                iv.setVisibility(View.VISIBLE);
//                iv.setBackgroundResource(R.drawable.icon_cat_ranking2);
//                break;
//            case 2:
//                iv.setVisibility(View.VISIBLE);
//                iv.setBackgroundResource(R.drawable.icon_cat_ranking3);
//                break;
//            default:
//                tv.setVisibility(View.VISIBLE);
//                tv.setText(String.valueOf(helper.getAdapterPosition() + 1));
//                break;
//        }

    }
}
