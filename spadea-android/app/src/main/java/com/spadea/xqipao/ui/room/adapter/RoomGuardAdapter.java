package com.spadea.xqipao.ui.room.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.makeramen.roundedimageview.RoundedImageView;
import com.spadea.yuyin.MyApplication;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.ImageLoader;
import com.spadea.xqipao.data.ProtectedRankingItemBean;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui.room.adapter
 * 创建人 王欧
 * 创建时间 2020/4/2 3:12 PM
 * 描述 describe
 */
public class RoomGuardAdapter extends BaseQuickAdapter<ProtectedRankingItemBean, BaseViewHolder> {
    public RoomGuardAdapter() {
        super(R.layout.rv_item_room_guard);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProtectedRankingItemBean item) {
        if (helper.getAdapterPosition() == 1) {
            helper.setBackgroundRes(R.id.tv_no, R.drawable.bg_guard_rank_2);
            helper.setTextColor(R.id.tv_no, MyApplication.getInstance().getResources().getColor(R.color.white));
        } else if (helper.getAdapterPosition() == 2) {
            helper.setBackgroundRes(R.id.tv_no, R.drawable.bg_guard_rank_3);
            helper.setTextColor(R.id.tv_no, MyApplication.getInstance().getResources().getColor(R.color.white));
        } else {
            helper.setBackgroundRes(R.id.tv_no, R.drawable.white_radius);
            helper.setTextColor(R.id.tv_no, MyApplication.getInstance().getResources().getColor(R.color.black));
        }
        helper.setText(R.id.tv_no,String.valueOf(helper.getAdapterPosition()+1));
        RoundedImageView rivAvatar = helper.getView(R.id.riv_avatar);
        ImageLoader.loadHead(MyApplication.getInstance().getApplicationContext(), rivAvatar, item.getHead_picture());
        helper.setText(R.id.tv_name, item.getNickname());
        helper.setText(R.id.tv_desc, String.format("%s位：剩余%s天", item.getType_name(),item.getDays()));
        RecyclerView recyclerView=helper.getView(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(new GuardMedalAdapter(item.getProtect_info()));

    }
}
