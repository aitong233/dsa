package com.qpyy.room.adapter;

import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qpyy.libcommon.db.DbController;
import com.qpyy.room.R;
import com.qpyy.room.bean.MusicResp;


public class SongsAdapter extends BaseQuickAdapter<MusicResp, BaseViewHolder> {

    private final DbController dbController;

    public SongsAdapter() {
        super(R.layout.room_rv_item_songs);
        dbController = DbController.getInstance(mContext);
    }

    @Override
    protected void convert(BaseViewHolder helper, MusicResp item) {
        helper.setText(R.id.tv_index, String.valueOf(helper.getAdapterPosition() + 1));
        helper.setText(R.id.tv_name, TextUtils.isEmpty(item.getTitle()) ? "" : item.getTitle());
        helper.setText(R.id.tv_auth, TextUtils.isEmpty(item.getAuthor()) ? "" : "-" + item.getAuthor());
        if (dbController.doesItExist(item.getSongid())) {
            helper.setGone(R.id.tv_add, false);
        } else {
            helper.setGone(R.id.tv_add, true);
            helper.addOnClickListener(R.id.tv_add);
        }

    }


}
