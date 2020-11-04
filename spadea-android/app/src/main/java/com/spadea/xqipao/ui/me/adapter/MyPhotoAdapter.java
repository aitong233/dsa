package com.spadea.xqipao.ui.me.adapter;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.spadea.xqipao.data.MyPhotoItem;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.ImageLoader;

import java.util.List;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui.me.adapter
 * 创建人 王欧
 * 创建时间 2020/5/24 3:22 PM
 * 描述 describe
 */
public class MyPhotoAdapter extends BaseQuickAdapter<MyPhotoItem, BaseViewHolder> {


    public MyPhotoAdapter(List<MyPhotoItem> list) {
        super(R.layout.rv_item_my_photos, list);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyPhotoItem item) {
        ImageView imageView = helper.getView(R.id.iv);
        CheckBox checkBox = helper.getView(R.id.cb);
        if (item.getSrc() == 0) {
            ImageLoader.loadImageCenterCrop(mContext, imageView, item.getUrl());
        } else {
            imageView.setImageResource(R.drawable.album_icon_sc);
        }
        checkBox.setVisibility(item.isEdit() ? View.VISIBLE : View.GONE);
        if (item.getSrc() != 0) {
            checkBox.setVisibility(View.GONE);
        }
        checkBox.setChecked(item.isChecked());
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                item.setChecked(isChecked);
            }
        });
    }

    public void setInEdit(boolean inEdit) {
        for (MyPhotoItem item : getData()) {
            item.setEdit(inEdit);
            item.setChecked(false);
        }
        notifyDataSetChanged();
    }

    public String getCheckedIds() {
        String ids = "";
        for (MyPhotoItem item : getData()) {
            if (item.isChecked()) {
                ids = ids + item.getId() + ",";
            }
        }
        try {
            ids = ids.substring(0, ids.length() - 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ids;
    }
}
