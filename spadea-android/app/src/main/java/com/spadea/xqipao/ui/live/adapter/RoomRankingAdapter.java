package com.spadea.xqipao.ui.live.adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.spadea.yuyin.MyApplication;
import com.spadea.xqipao.data.RoomRankingModel;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.ImageLoader;
import com.makeramen.roundedimageview.RoundedImageView;

public class RoomRankingAdapter extends BaseQuickAdapter<RoomRankingModel, BaseViewHolder> {


    public RoomRankingAdapter() {
        super(R.layout.item_room_ranking);
    }

    @Override
    protected void convert(BaseViewHolder helper, RoomRankingModel item) {
        helper.setText(R.id.tv_ranking, item.getRank() + "")
                .setText(R.id.tv_room_name, item.getName())
                .setText(R.id.tv_num, "魅力值 " + item.getNumber_format());
        RoundedImageView roundedImageView = helper.getView(R.id.riv);
        ImageLoader.loadHead(MyApplication.getInstance(), roundedImageView, item.getPicture());
    }


}
