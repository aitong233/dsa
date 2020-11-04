package com.qpyy.room.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.adapter
 * 创建人 黄强
 * 创建时间 2020/7/30 18:26
 * 描述 describe
 */
public class RoomAdminListAdapter extends BaseQuickAdapter {

    public RoomAdminListAdapter(int layoutResId, @Nullable List data) {
        super(layoutResId, data);
    }

    public RoomAdminListAdapter(@Nullable List data) {
        super(data);
    }

    public RoomAdminListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, Object item) {

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

}
