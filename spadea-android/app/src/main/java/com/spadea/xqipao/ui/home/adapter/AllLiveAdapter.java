package com.spadea.xqipao.ui.home.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.spadea.xqipao.data.RoomModel;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.ImageLoader;

import java.util.Random;

public class AllLiveAdapter extends BaseQuickAdapter<RoomModel, BaseViewHolder> {


    private Integer[] background = new Integer[]{R.mipmap.bg_room1, R.mipmap.bg_room2, R.mipmap.bg_room3};

    public AllLiveAdapter() {
        super(R.layout.item_all_live);
    }

    @Override
    protected void convert(BaseViewHolder helper, RoomModel item) {
        if (item.getLast_join() == null) {
            helper.setVisible(R.id.ll_welcome, false);
        } else {
            helper.setVisible(R.id.ll_welcome, true);
            helper.setText(R.id.tv_text, "@" + item.getLast_join().getNickname() + "   欢迎进入本房间");
            ImageLoader.loadHead(mContext, helper.getView(R.id.riv_user_head_img), item.getLast_join().getHead_picture());
        }
        ImageLoader.loadHead(mContext, helper.getView(R.id.riv_head), item.getOwner_picture());
        if (item.getHolder().equals("0")) {
            ImageLoader.loadHead(mContext, helper.getView(R.id.riv_head_img), item.getOwner_picture());
            helper.setText(R.id.tv_nickname, item.getOwner_nickname());
        } else {
            ImageLoader.loadHead(mContext, helper.getView(R.id.riv_head_img), item.getHolder_picture());
            helper.setText(R.id.tv_nickname, item.getHolder_nickname());
        }

        if (item.getHave_password() == 0) {
            helper.setVisible(R.id.iv_room_lock, false);
        } else {
            helper.setVisible(R.id.iv_room_lock, true);
        }
        helper.setBackgroundRes(R.id.rl, background[new Random().nextInt(background.length)])
                .setText(R.id.tv_title, item.getRoom_name())
                .setText(R.id.tv_host_num, item.getPopularity());

        helper.addOnClickListener(R.id.iv_more);
        helper.addOnClickListener(R.id.riv_head_img);
//        helper.addOnClickListener(R.id.tv_together);

    }
}
