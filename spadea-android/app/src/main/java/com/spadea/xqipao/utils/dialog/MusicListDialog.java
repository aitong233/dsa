package com.spadea.xqipao.utils.dialog;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qpyy.libcommon.db.DbController;
import com.qpyy.libcommon.db.table.MusicTable;
import com.spadea.yuyin.R;
import com.spadea.xqipao.common.Constant;
import com.spadea.xqipao.data.even.MusicDelEvent;
import com.spadea.xqipao.utils.SPUtil;

import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MusicListDialog extends BottomSheetDialog {

    @BindView(R.id.iv_music_play_pattern)
    ImageView ivMusicPlayPattern;
    @BindView(R.id.tv_music_num)
    TextView tvMusicNum;
    @BindView(R.id.tv_upload)
    TextView tvUpload;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.tv_music_list_close)
    TextView tvMusicListClose;
    private Context mContext;
    private MusicListClick mMusicListClick;
    private MusicListAdapter musicListAdapter;
    private int playPattern;

    public MusicListDialog(@NotNull Context mContext) {
        super(mContext);
        this.mContext = mContext;
        initViews();
    }

    private View dialogView;

    private void initViews() {
        dialogView = LayoutInflater.from(mContext).inflate(R.layout.dialog_music_list, null, false);
        ButterKnife.bind(this, dialogView);
        setContentView(dialogView);
        ((View) dialogView.getParent()).setBackgroundColor(Color.parseColor("#00000000"));
        recyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerview.setAdapter(musicListAdapter = new MusicListAdapter());
        musicListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                MusicTable item = musicListAdapter.getItem(position);
                if (item.getSongid() != musicListAdapter.getSongid()) {
                    List<MusicTable> data = musicListAdapter.getData();
                    DbController.getInstance(mContext).deleteMusicBy(data.get(position));
                    data.remove(position);
                    musicListAdapter.setNewData(data);
                    EventBus.getDefault().post(new MusicDelEvent());
                    getMusicCount();
                }
            }
        });
        musicListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (mMusicListClick != null) {
                    mMusicListClick.startPlayMusic(musicListAdapter.getItem(position), position);
//                        musicListAdapter.setSongid(musicListAdapter.getItem(position));
                }
            }
        });
    }


    public void setMusicListData(List<MusicTable> listData, MusicTable musicTable) {
        musicListAdapter.setNewData(listData);
//        musicListAdapter.setSongid(musicTable);
        getMusicCount();
    }

    public void setSongid(MusicTable musicTable) {
//        musicListAdapter.setSongid(musicTable);
    }


    private void getMusicCount() {
        int i = DbController.getInstance(mContext).queryMUsicCount();
        playPattern = SPUtil.getInt(Constant.PLAY_MODE, 0);
        ivMusicPlayPattern.setImageLevel(playPattern);
        switch (playPattern) {
            case 0:
                tvMusicNum.setText("顺序播放(" + i + ")首");
                break;
            case 1:
                tvMusicNum.setText("随机播放(" + i + ")首");
                break;
            case 2:
                tvMusicNum.setText("单曲循环(" + i + ")首");
                break;
        }
    }

    @OnClick({R.id.tv_upload, R.id.tv_music_list_close, R.id.iv_music_play_pattern})
    public void onClick(View view) {
        if (mMusicListClick != null) {
            switch (view.getId()) {
                case R.id.tv_upload:
                    mMusicListClick.upLoad();
                    break;
                case R.id.tv_music_list_close:
                    mMusicListClick.close();
                    break;
                case R.id.iv_music_play_pattern:
                    if (playPattern == 0) {
                        playPattern = 1;
                    } else if (playPattern == 1) {
                        playPattern = 2;
                    } else {
                        playPattern = 0;
                    }
                    ivMusicPlayPattern.setImageLevel(playPattern);
                    SPUtil.saveInt(Constant.PLAY_MODE, playPattern);
                    mMusicListClick.playPattern(playPattern);
                    break;
            }
        }
    }

    public void setTitle(String title) {
        tvMusicNum.setText(title);
    }

    public void addMusicListClick(MusicListClick mMusicListClick) {
        this.mMusicListClick = mMusicListClick;
    }


    @Override
    public void show() {
        super.show();
    }

    public interface MusicListClick {
        void upLoad();

        void close();

        void startPlayMusic(MusicTable item, int position);

        void playPattern(int playPattern);

        void stopMusicPlay();
    }


}
