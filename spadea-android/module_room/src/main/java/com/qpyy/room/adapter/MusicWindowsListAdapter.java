package com.qpyy.room.adapter;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qpyy.libcommon.db.table.MusicTable;
import com.qpyy.room.R;
import com.qpyy.room.bean.MusicResp;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.adapter
 * 创建人 黄强
 * 创建时间 2020/8/5 18:38
 * 描述 describe
 */
public class MusicWindowsListAdapter extends BaseQuickAdapter<MusicTable, BaseViewHolder> {

    private int songId = 0;//歌曲ID
    private boolean isPlay = false;

    public MusicWindowsListAdapter() {
        super(R.layout.room_rv_item_music_dialog_list);
    }

    @Override
    protected void convert(BaseViewHolder helper, MusicTable item) {

        TextView tvTitle = helper.getView(R.id.tv_music_name);
        TextView tvMusicName = helper.getView(R.id.tv_music_singer_list);
        ImageView ivMusic = helper.getView(R.id.iv_music_list_label_icon);
        tvTitle.setText(item.getTitle() == null ? "" : item.getTitle());
        tvMusicName.setText(item.getAuthor() == null ? "" : " - " + item.getAuthor());
        ivMusic.setVisibility(item.getSongid() == songId && isPlay ? View.VISIBLE : View.GONE);
        helper.addOnClickListener(R.id.iv_music_list_delete);

    }

    public void setSongId(int id, boolean isPlay) {//设置正在播放的歌曲
        songId = id;
        this.isPlay = isPlay;
        notifyDataSetChanged();
    }


    public int getSongId() {
        return songId;
    }
}
