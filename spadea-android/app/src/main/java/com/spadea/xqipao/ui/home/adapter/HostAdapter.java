package com.spadea.xqipao.ui.home.adapter;

import android.text.TextUtils;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.ImageLoader;
import com.spadea.xqipao.data.RoomModel;
import com.spadea.xqipao.utils.LabelUtil;

public class HostAdapter extends BaseQuickAdapter<RoomModel, BaseViewHolder> {


    public HostAdapter() {
        super(R.layout.item_hot_room);
    }

    @Override
    protected void convert(BaseViewHolder helper, RoomModel item) {

        TextView tvSex = helper.getView(R.id.tv_sex);

        helper.setVisible(R.id.iv_week_star, item.getWeek_star_recommend() == 1);

        if (TextUtils.isEmpty(item.getHolder_nickname())) {
            helper.setText(R.id.tv_host, "暂无主持");
        } else {
            helper.setText(R.id.tv_host, "主持人：" + item.getHolder_nickname());
        }

        LabelUtil.setLabel(item.getLabel_name(), tvSex);

        ImageLoader.loadImage(mContext, helper.getView(R.id.riv), item.getOwner_picture());
        helper.setText(R.id.tv_room_name, item.getRoom_name())
                .setText(R.id.tv_host_num, item.getPopularity());

        if (item.getHave_password() == 0) {
            helper.setVisible(R.id.iv_room_lock, false);
        } else {
            helper.setVisible(R.id.iv_room_lock, true);
        }
    }


}
