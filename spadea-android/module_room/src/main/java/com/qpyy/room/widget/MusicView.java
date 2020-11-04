package com.qpyy.room.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.qpyy.libcommon.db.DbController;
import com.qpyy.libcommon.db.table.MusicTable;
import com.qpyy.libcommon.utils.SpUtils;
import com.qpyy.libcommon.widget.MarqueeTextView;
import com.qpyy.room.R;
import com.qpyy.room.R2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MusicView extends FrameLayout {

    @BindView(R2.id.tv_music_title)
    MarqueeTextView tvMusicTitle;
    @BindView(R2.id.tv_singer)
    MarqueeTextView tvSinger;
    @BindView(R2.id.iv_minx)
    ImageView ivMinx;
    @BindView(R2.id.iv_pattern)
    ImageView ivPattern;
    @BindView(R2.id.iv_last)
    ImageView ivLast;
    @BindView(R2.id.iv_music_play_state)
    ImageView ivMusicPlayState;
    @BindView(R2.id.iv_next)
    ImageView ivNext;
    @BindView(R2.id.iv_list)
    ImageView ivList;
    @BindView(R2.id.cl_music_btn_layout)
    ConstraintLayout clMusicBtnLayout;
    @BindView(R2.id.seek_bar)
    SeekBar seekBar;
    private Context mContext;

    private int playPattern = 1;  //1循环播放  2单曲循环  3随机播放
    private boolean isPaly = false;
    private MusicTable mMusicTable;
    private OnItemClickListener mOnItemClickListener;
    private List<MusicTable> musicTables = new ArrayList<>();

    public MusicView(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public MusicView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public MusicView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        this.mContext = context;
        LayoutInflater.from(context).inflate(R.layout.room_dialog_music_window_open, this, true);
        ButterKnife.bind(this);
        playPattern = SpUtils.getPlayPattern();
        initListener();
        switch (playPattern) {
            case 1:
                ivPattern.setImageResource(R.mipmap.room_music_win_circulation);
                break;
            case 2:
                ivPattern.setImageResource(R.mipmap.room_music_win_singlecircle);
                break;
            case 3:
                ivPattern.setImageResource(R.mipmap.room_music_win_random);
                break;
        }
        seekBar.setProgress(SpUtils.getChannelVolume());
    }

    private void initListener() {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                if (b) {
                    if (progress < 0) {
                        progress = 0;
                    }
                    if (progress > 100) {
                        progress = 100;
                    }
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.setMusicVolume(progress);
                    }
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    //icon_music_stop
    public void setPalyState(boolean b) {
        this.isPaly = b;
        if (isPaly) {
            ivMusicPlayState.setImageResource(R.mipmap.room_music_win_start);
        } else {
            ivMusicPlayState.setImageResource(R.mipmap.room_music_win_puase);
        }
    }

    public void setData(MusicTable musicTable) {
        this.mMusicTable = musicTable;
        tvMusicTitle.setText(mMusicTable.getTitle());
        tvSinger.setText(mMusicTable.getAuthor());
    }


    @OnClick({R2.id.iv_minx, R2.id.iv_music_play_state, R2.id.iv_list, R2.id.iv_next, R2.id.iv_last, R2.id.iv_pattern})
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.iv_minx) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.minimize();
            }
        } else if (id == R.id.iv_list) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.openMusicList();
            }
        } else if (id == R.id.iv_music_play_state) {
            if (mOnItemClickListener != null) {
                if (isPaly) {
                    mOnItemClickListener.pausePlay();
                } else {
                    mOnItemClickListener.resumePlay();
                }
            }
        } else if (id == R.id.iv_next) {
            next();
        } else if (id == R.id.iv_last) {
            last();
        } else if (id == R.id.iv_pattern) {
            if (playPattern == 1) {
                playPattern = 2;
            } else if (playPattern == 2) {
                playPattern = 3;
            } else if (playPattern == 3) {
                playPattern = 1;
            }
            switch (playPattern) {
                case 1:
                    ivPattern.setImageResource(R.mipmap.room_music_win_circulation);
                    break;
                case 2:
                    ivPattern.setImageResource(R.mipmap.room_music_win_singlecircle);
                    break;
                case 3:
                    ivPattern.setImageResource(R.mipmap.room_music_win_random);
                    break;
            }
            SpUtils.setPlayPattern(playPattern);
        }

    }


    public void addOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void setMusicList(List<MusicTable> lovalMusicData) {
        this.musicTables = lovalMusicData;
    }


    public interface OnItemClickListener {
        void minimize();

        void openMusicList();

        void playMusic(MusicTable musicTable);

        void stopPlay();

        void pausePlay();

        void resumePlay();


        void setMusicVolume(int progress);
    }


    public void initData() {
        musicTables = DbController.getInstance(mContext).queryMusicListAll();
    }

    public void next() {
        initData();
        if (musicTables.size() == 0) {
            return;
        }
        musicTables.size();
        int index = 0;
        for (int i = 0; i < musicTables.size(); i++) {
            if (SpUtils.getPlayCurrentMusic() == musicTables.get(i).getSongid()) {
                index = i;
                break;
            }
        }
        index++;
        if (index == musicTables.size()) {
            mMusicTable = musicTables.get(0);
        } else {
            mMusicTable = musicTables.get(index);
        }
        if (mOnItemClickListener != null) {
            mOnItemClickListener.playMusic(mMusicTable);
        }
        setData(mMusicTable);
    }

    public void last() {
        initData();
        int index = 0;
        for (int i = 0; i < musicTables.size(); i++) {
            if (mMusicTable.getId().equals(musicTables.get(i).getId())) {
                index = i;
                break;
            }
        }
        index--;
        if (index <= 0) {
            mMusicTable = musicTables.get(musicTables.size() - 1);
        } else {
            mMusicTable = musicTables.get(index);
        }
        if (mOnItemClickListener != null) {
            mOnItemClickListener.playMusic(mMusicTable);
        }
        setData(mMusicTable);
    }


}
