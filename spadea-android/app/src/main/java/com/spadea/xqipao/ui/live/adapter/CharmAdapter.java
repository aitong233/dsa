package com.spadea.xqipao.ui.live.adapter;


import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.spadea.yuyin.MyApplication;
import com.spadea.xqipao.data.CharmModel;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.ImageLoader;
import com.chad.library.adapter.base.BaseViewHolder;
import com.makeramen.roundedimageview.RoundedImageView;

public class CharmAdapter extends BaseQuickAdapter<CharmModel.ListsBean, BaseViewHolder> {


    private int type = 0;

    public CharmAdapter(int type) {
        super(R.layout.item_ranking_charm);
        this.type = type;
    }

    @Override
    protected void convert(BaseViewHolder helper, CharmModel.ListsBean item) {
        helper.setText(R.id.tv_index, item.getRank() + "")
                .setText(R.id.tv_name, item.getNickname());
        RoundedImageView roundedImageView = helper.getView(R.id.riv);
        ImageLoader.loadHead(MyApplication.getInstance(), roundedImageView, item.getHead_picture());
        TextView tvCharmNum = helper.getView(R.id.tv_charm);
        if (type == 0) {
            tvCharmNum.setText("魅力值 " + item.getNumber());
        } else {
            tvCharmNum.setText("财富值 " + item.getNumber());
        }
    }


}
