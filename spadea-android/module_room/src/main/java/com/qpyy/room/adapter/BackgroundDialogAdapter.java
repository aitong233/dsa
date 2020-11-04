package com.qpyy.room.adapter;

import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qpyy.libcommon.utils.ImageUtils;
import com.qpyy.room.R;
import com.qpyy.room.bean.RoomBgBean;

import org.greenrobot.eventbus.EventBus;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.adapter
 * 创建人 黄强
 * 创建时间 2020/8/11 19:09
 * 描述 describe
 */
public class BackgroundDialogAdapter extends BaseQuickAdapter<RoomBgBean, BaseViewHolder> {


    private String checkedPicture;

    public BackgroundDialogAdapter() {
        super(R.layout.room_rv_item_room_bg);
    }

    public void setCheckedPicture(String checkedPicture) {
        this.checkedPicture = checkedPicture;
    }

    public String getCheckedPicture() {
        return checkedPicture;
    }

    @Override
    protected void convert(BaseViewHolder helper, RoomBgBean item) {
        if (TextUtils.isEmpty(item.getPicture())) {
//                ImageUtils.loadImageView(BaseApplication.getIns().getUser().getHead_picture(), helper.getView(R.id.iv_bg));
        } else {
            ImageUtils.loadImageView(item.getPicture(), helper.getView(R.id.iv_bg));
        }
        if (TextUtils.isEmpty(item.getName())) {
            helper.setVisible(R.id.tv_name, false);
        } else {
            helper.setVisible(R.id.tv_name, true);
            helper.setText(R.id.tv_name, item.getName());
        }
        if ((TextUtils.isEmpty(checkedPicture) && helper.getAdapterPosition() == 0) || (!(TextUtils.isEmpty(checkedPicture)) && checkedPicture.equals(item.getPicture()))) {
            helper.setBackgroundRes(R.id.cl, R.drawable.room_bg_room_bg_item_selected);
        } else {
            helper.setBackgroundRes(R.id.cl, R.drawable.room_bg_room_bg_item_normal);
        }
        helper.getView(R.id.cl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkedPicture = item.getPicture();
                EventBus.getDefault().post(item);
                notifyDataSetChanged();
            }
        });

    }
}
