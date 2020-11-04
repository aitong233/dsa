package com.qpyy.module.index.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.data
 * 创建人 王欧
 * 创建时间 2020/5/6 2:42 PM
 * 描述 describe
 */
public class MultiRoomModel implements MultiItemEntity {

    public int itemType;

    public RoomModel mRoomModel;

    public MultiRoomModel(int itemType, RoomModel roomModel) {
        this.itemType = itemType;
        mRoomModel = roomModel;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
