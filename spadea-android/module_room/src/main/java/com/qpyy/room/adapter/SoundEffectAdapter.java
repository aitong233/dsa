package com.qpyy.room.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qpyy.libcommon.base.BaseApplication;
import com.qpyy.libcommon.utils.ImageUtils;
import com.qpyy.room.R;
import com.qpyy.room.bean.RoomSceneItem;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.adapter
 * 创建人 王欧
 * 创建时间 2020/8/16 5:36 PM
 * 描述 describe
 */
public class SoundEffectAdapter extends BaseQuickAdapter<RoomSceneItem, BaseViewHolder> {

    private int sceneType;

    public SoundEffectAdapter(int sceneType) {
        super(R.layout.room_rv_item_sound_effect);
        this.sceneType = sceneType;
    }

    @Override
    protected void convert(BaseViewHolder helper, RoomSceneItem item) {
        helper.setText(R.id.tv_room_info_sound_style_up_txt, item.getName());
        helper.setText(R.id.tv_room_info_sound_style_dw_txt, item.getInfo());
        if (sceneType == item.getId()) {
            ImageUtils.loadImageView(item.getIcon_select(), helper.getView(R.id.iv_room_info_sound_style_item_icon));
            helper.setTextColor(R.id.tv_room_info_sound_style_up_txt, BaseApplication.getIns().getResources().getColor(R.color.color_FF6765FF));
            helper.setTextColor(R.id.tv_room_info_sound_style_dw_txt, BaseApplication.getIns().getResources().getColor(R.color.color_FFC1C0FF));
            helper.setBackgroundRes(R.id.iv_room_info_sound_style_check, R.drawable.room_sound_item_focus_bg);
        } else {
            helper.setTextColor(R.id.tv_room_info_sound_style_up_txt, BaseApplication.getIns().getResources().getColor(R.color.color_FFA6A6A6));
            helper.setTextColor(R.id.tv_room_info_sound_style_dw_txt, BaseApplication.getIns().getResources().getColor(R.color.color_FFAFAFAF));
            ImageUtils.loadImageView(item.getIcon(), helper.getView(R.id.iv_room_info_sound_style_item_icon));
            helper.setBackgroundRes(R.id.iv_room_info_sound_style_check, R.drawable.room_sound_item_normal_bg);
        }
    }

    public void setSceneType(int sceneType) {
        this.sceneType = sceneType;
        notifyDataSetChanged();
    }

}
