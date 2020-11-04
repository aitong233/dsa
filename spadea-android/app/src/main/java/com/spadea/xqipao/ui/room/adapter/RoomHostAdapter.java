package com.spadea.xqipao.ui.room.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.makeramen.roundedimageview.RoundedImageView;
import com.spadea.yuyin.MyApplication;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.ImageLoader;
import com.spadea.xqipao.data.AnchorRankingItemBean;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui.room.adapter
 * 创建人 王欧
 * 创建时间 2020/4/2 5:11 PM
 * 描述 describe
 */
public class RoomHostAdapter extends BaseQuickAdapter<AnchorRankingItemBean, BaseViewHolder> {
    public RoomHostAdapter() {
        super(R.layout.rv_item_room_host);
    }

    @Override
    protected void convert(BaseViewHolder helper, AnchorRankingItemBean item) {
        if (item.getRank() < 4) {
            helper.setTextColor(R.id.tv_no, MyApplication.getInstance().getResources().getColor(R.color.color_ff8890));
        } else {
            helper.setTextColor(R.id.tv_no, MyApplication.getInstance().getResources().getColor(R.color.black));
        }
        helper.setText(R.id.tv_no, String.valueOf(item.getRank()));
        RoundedImageView rivAvatar = helper.getView(R.id.riv_avatar);
        ImageLoader.loadHead(MyApplication.getInstance().getApplicationContext(), rivAvatar, item.getHead_picture());
        helper.setText(R.id.tv_name, item.getNickname());
        helper.setText(R.id.tv_desc, String.format("魅力:%s", item.getNumber()));
        if (item.getIs_follow() == 1) {
            helper.setText(R.id.tv_follow, "已关注");
        } else {
            helper.setText(R.id.tv_follow, "关注");
        }
        helper.addOnClickListener(R.id.tv_follow);
    }
}
