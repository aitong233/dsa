package com.spadea.xqipao.utils.view.room;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.qpyy.libcommon.db.DbController;
import com.qpyy.libcommon.db.table.MusicTable;
import com.spadea.yuyin.R;
import com.spadea.xqipao.common.Constant;
import com.spadea.xqipao.utils.SPUtil;
import com.spadea.xqipao.utils.view.MarqueeTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MusicView extends FrameLayout {

    @BindView(R.id.tv_music_title)
    MarqueeTextView tvMusicTitle;
    @BindView(R.id.tv_singer)
    TextView tvSinger;
    @BindView(R.id.iv_minx)
    ImageView ivMinx;
    @BindView(R.id.iv_pattern)
    ImageView ivPattern;
    @BindView(R.id.iv_list)
    ImageView ivList;
    @BindView(R.id.iv_music_play_state)
    ImageView ivMusicPlayState;
    @BindView(R.id.iv_last)
    ImageView ivLast;
    @BindView(R.id.iv_next)
    ImageView ivNext;
    @BindView(R.id.seekbar)
    SeekBar seekbar;
    private Context mContext;

    private int playPattern = 1;  //1循环播放  2单曲循环
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
        LayoutInflater.from(context).inflate(R.layout.layout_view_music, this, true);
        ButterKnife.bind(this);
        playPattern = SPUtil.getInt("playPattern", 1);
        initListener();
        switch (playPattern) {
            case 1:
                ivPattern.setImageResource(R.mipmap.icon_pattern);
                break;
            case 2:
                ivPattern.setImageResource(R.mipmap.icon_one);
                break;
        }
        seekbar.setProgress(SPUtil.getInt(Constant.Channel.VOLUME, 20));
    }

    private void initListener() {
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
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
                        mOnItemClickListener.setMmusicvolume(progress);
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
            ivMusicPlayState.setImageResource(R.mipmap.icon_music_play);
        } else {
            ivMusicPlayState.setImageResource(R.mipmap.icon_music_stop);
        }
    }

    public void setData(MusicTable musicTable) {
        this.mMusicTable = musicTable;
        tvMusicTitle.setText(mMusicTable.getTitle());
        tvSinger.setText(mMusicTable.getAuthor());
    }


    @OnClick({R.id.iv_minx, R.id.iv_music_play_state, R.id.iv_list, R.id.iv_next, R.id.iv_last, R.id.iv_pattern})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_minx:
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.minimize();
                }
                break;
            case R.id.iv_list:
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.openMusicList();
                }
                break;
            case R.id.iv_music_play_state:
                if (mOnItemClickListener != null) {
                    if (isPaly) {
                        mOnItemClickListener.stopPlay();
                    } else {
                        mOnItemClickListener.palyMusic(mMusicTable);
                    }
                }
                break;
            case R.id.iv_next:
                next();
                break;
            case R.id.iv_last:
                last();
                break;
            case R.id.iv_pattern:
                if (playPattern == 1) {
                    playPattern = 2;
                } else {
                    playPattern = 1;
                }
                switch (playPattern) {
                    case 1:
                        ivPattern.setImageResource(R.mipmap.icon_pattern);
                        break;
                    case 2:
                        ivPattern.setImageResource(R.mipmap.icon_one);
                        break;
                }
                SPUtil.saveInt("playPattern", playPattern);
                break;
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

        void palyMusic(MusicTable musicTable);

        void stopPlay();

        void setMmusicvolume(int progress);
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
            if (mMusicTable.getId().equals(musicTables.get(i).getId())) {
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
            mOnItemClickListener.palyMusic(mMusicTable);
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
            mOnItemClickListener.palyMusic(mMusicTable);
        }
        setData(mMusicTable);
    }

}
