package com.spadea.xqipao.ui.home.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.spadea.xqipao.data.SearchRoomInfo;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.ImageLoader;

public class SearchRoomAdapter extends BaseQuickAdapter<SearchRoomInfo, BaseViewHolder> {

    public SearchRoomAdapter() {
        super(R.layout.item_search_room);
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchRoomInfo item) {
        ImageLoader.loadHead(mContext, helper.getView(R.id.riv), item.getCover_picture());
        helper.setText(R.id.tv_host, "主持人：" + item.getNickname())
                .setText(R.id.tv_nick_name, item.getRoom_name()).setText(R.id.tv_host_num, item.getPopularity())
                .setText(R.id.tv_sex, item.getLabel_name())
        ;
    }
}
