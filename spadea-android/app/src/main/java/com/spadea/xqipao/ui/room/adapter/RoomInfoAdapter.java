package com.spadea.xqipao.ui.room.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.spadea.yuyin.MyApplication;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.ImageLoader;
import com.spadea.xqipao.data.RoomExtraModel;


public class RoomInfoAdapter extends BaseQuickAdapter<RoomExtraModel.ManagerListBean, BaseViewHolder> {


    public RoomInfoAdapter() {
        super(R.layout.item_roominfo);
    }

    @Override
    protected void convert(BaseViewHolder helper, RoomExtraModel.ManagerListBean item) {
        ImageLoader.loadHead(MyApplication.getInstance(), helper.getView(R.id.riv), item.getHead_picture());
    }
}
