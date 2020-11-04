package com.spadea.xqipao.utils.dialog;

import android.graphics.Color;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qpyy.libcommon.db.table.MusicTable;
import com.spadea.yuyin.R;

public class MusicListAdapter extends BaseQuickAdapter<MusicTable, BaseViewHolder> {


    private int songid = 0;

    public MusicListAdapter() {
        super(R.layout.item_music_list);
    }

    @Override
    protected void convert(BaseViewHolder helper, MusicTable item) {
        TextView tvTitle = helper.getView(R.id.tv_title);
        TextView tvMusicName = helper.getView(R.id.tv_music_name);
        ImageView ivMusic = helper.getView(R.id.iv_music_list_del);
        tvTitle.setText(item.getTitle() == null ? "" : item.getTitle());
        tvMusicName.setText(item.getAuthor() == null ? "" : " - " + item.getAuthor());

        if (item.getSongid() == songid) {
            tvTitle.setTextColor(Color.parseColor("#FFF96791"));
            tvMusicName.setTextColor(Color.parseColor("#FFF96791"));
            ivMusic.setImageResource(R.drawable.icon_music_here);
        } else {
            tvTitle.setTextColor(Color.parseColor("#FFFFFFFF"));
            tvMusicName.setTextColor(Color.parseColor("#FFFFFFFF"));
            ivMusic.setImageResource(R.drawable.icon_music_list_del);
        }
        helper.addOnClickListener(R.id.iv_music_list_del);
    }

    public void setSongid(int id) {
        songid = id;
        notifyDataSetChanged();
    }


    public int getSongid() {
        return songid;
    }
}
