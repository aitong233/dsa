package com.spadea.xqipao.ui.room.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.spadea.xqipao.data.ManageRoomModel;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.ImageLoader;


public class MeRoomAdapter extends BaseQuickAdapter<ManageRoomModel, BaseViewHolder> {

    private int type = 0;

    public MeRoomAdapter(int type) {
        super(R.layout.item_me_room);
        this.type = type;
    }

    @Override
    protected void convert(BaseViewHolder helper, ManageRoomModel item) {
        ImageLoader.loadHead(mContext, helper.getView(R.id.riv), item.getCover_picture());
        helper.setText(R.id.tv_room_name, item.getRoom_name())
                .setText(R.id.tv_jost_num, item.getPopularity());
        if (type == 0) {
            helper.setVisible(R.id.tv_identity, true);
            helper.setText(R.id.tv_identity, "管理员");
        } else {
            helper.setVisible(R.id.tv_identity, false);
        }
    }
}
