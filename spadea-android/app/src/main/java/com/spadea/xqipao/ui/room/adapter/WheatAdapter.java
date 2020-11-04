package com.spadea.xqipao.ui.room.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.spadea.yuyin.MyApplication;
import com.spadea.xqipao.data.RowWheatModel;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.ImageLoader;


public class WheatAdapter extends BaseQuickAdapter<RowWheatModel, BaseViewHolder> {


    public WheatAdapter() {
        super(R.layout.item_wheat);
    }

    @Override
    protected void convert(BaseViewHolder helper, RowWheatModel item) {
        ImageLoader.loadHead(MyApplication.getInstance(), helper.getView(R.id.riv), item.getHead_picture());
        helper.setText(R.id.tv_nick_name, item.getNickname())
                .setText(R.id.tv_index, (helper.getAdapterPosition() + 1) + "");
    }
}
