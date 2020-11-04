package com.qpyy.room.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.makeramen.roundedimageview.RoundedImageView;
import com.qpyy.libcommon.utils.ImageUtils;
import com.qpyy.room.R;
import com.qpyy.room.bean.RoomExtraModel;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.adapter
 * 创建人 黄强
 * 创建时间 2020/7/31 14:51
 * 描述 describe
 */
public class RoomAdminOrBlacklistAdapter extends BaseQuickAdapter<RoomExtraModel.ManagerListBean, BaseViewHolder> {

    public RoomAdminOrBlacklistAdapter() {
        super(R.layout.room_rv_item_manage);
    }

    @Override
    protected void convert(BaseViewHolder helper, RoomExtraModel.ManagerListBean item) {
        ImageUtils.loadImageView(item.getHead_picture(),helper.getView(R.id.riv_pic));
        helper.addOnClickListener(R.id.iv_delete);//添加子控件点击事件监听
    }
}
