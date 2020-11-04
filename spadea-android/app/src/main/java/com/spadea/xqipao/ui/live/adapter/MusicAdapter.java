package com.spadea.xqipao.ui.live.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qpyy.libcommon.db.DbController;
import com.spadea.yuyin.MyApplication;
import com.spadea.xqipao.data.MusicModel;
import com.spadea.yuyin.R;


public class MusicAdapter extends BaseQuickAdapter<MusicModel, BaseViewHolder> {

    public MusicAdapter() {
        super(R.layout.item_music_search_list);
    }

    @Override
    protected void convert(BaseViewHolder helper, MusicModel item) {
        helper.setText(R.id.tv_index, String.valueOf(helper.getAdapterPosition() + 1))
                .setText(R.id.tv_name, item.getTitle() == null ? "" : item.getTitle());
        TextView tvMusicAuth = helper.getView(R.id.tv_music_auth);
        if (!TextUtils.isEmpty(item.getAuthor())) {
            if (!TextUtils.isEmpty(item.getAlbum())) {
                tvMusicAuth.setText(item.getAuthor() + " . " + item.getAlbum());
            } else {
                tvMusicAuth.setText(item.getAuthor());
            }
        } else {
            tvMusicAuth.setText(item.getAlbum());
        }

        TextView tvSize = helper.getView(R.id.tv_size);
        if (item.getSize() == 0) {
            tvSize.setVisibility(View.GONE);
        } else {
            tvSize.setVisibility(View.VISIBLE);
            tvSize.setText(getPrintSize(item.getSize()));
        }

        ImageView ivAddMusic = helper.getView(R.id.iv_add_music);
        boolean b = DbController.getInstance(MyApplication.getInstance()).doesItExist(item.getSongid());
        if (b) {
            ivAddMusic.setVisibility(View.INVISIBLE);
        } else {
            ivAddMusic.setVisibility(View.VISIBLE);
        }
        helper.addOnClickListener(R.id.iv_add_music);
    }

    public static String getPrintSize(long size) {
        //如果字节数少于1024，则直接以B为单位，否则先除于1024，后3位因太少无意义
        if (size < 1024) {
            return String.valueOf(size) + "B";
        } else {
            size = size / 1024;
        }
        //如果原字节数除于1024之后，少于1024，则可以直接以KB作为单位
        //因为还没有到达要使用另一个单位的时候
        //接下去以此类推
        if (size < 1024) {
            return String.valueOf(size) + "KB";
        } else {
            size = size / 1024;
        }
        if (size < 1024) {
            //因为如果以MB为单位的话，要保留最后1位小数，
            //因此，把此数乘以100之后再取余
            size = size * 100;
            return String.valueOf((size / 100)) + "."
                    + String.valueOf((size % 100)) + "MB";
        } else {
            //否则如果要以GB为单位的，先除于1024再作同样的处理
            size = size * 100 / 1024;
            return String.valueOf((size / 100)) + "."
                    + String.valueOf((size % 100)) + "GB";
        }
    }

}
