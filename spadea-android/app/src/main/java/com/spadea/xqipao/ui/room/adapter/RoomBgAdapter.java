package com.spadea.xqipao.ui.room.adapter;

import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.spadea.yuyin.MyApplication;
import com.spadea.xqipao.data.RoomExtraModel;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.ImageLoader;

public class RoomBgAdapter extends BaseQuickAdapter<RoomExtraModel.BgBean, BaseViewHolder> {


    private String picture = "";

    public RoomBgAdapter() {
        super(R.layout.item_room_bg);
    }

    @Override
    protected void convert(BaseViewHolder helper, RoomExtraModel.BgBean item) {
        RelativeLayout rl = helper.getView(R.id.rl);
        if (item.getPicture().equals(picture)) {
            rl.setBackgroundResource(R.drawable.bg_r4_fff6fb_colormain);
        } else {
            rl.setBackgroundResource(0);
        }
        ImageLoader.loadImage(MyApplication.getInstance(), helper.getView(R.id.riv_room_cover), item.getPicture());
    }


    public void setPicture(String picture) {
        this.picture = picture;
        notifyDataSetChanged();
    }
}
