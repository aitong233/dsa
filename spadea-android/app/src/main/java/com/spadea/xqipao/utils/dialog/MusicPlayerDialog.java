package com.spadea.xqipao.utils.dialog;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.qpyy.libcommon.db.table.MusicTable;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.utilcode.ToastUtils;
import com.spadea.xqipao.common.Constant;
import com.spadea.xqipao.utils.SPUtil;
import com.spadea.xqipao.utils.view.MarqueeTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//return R.layout.dialog_music_player;
public class MusicPlayerDialog extends BottomSheetDialog {

    @BindView(R.id.iv_music_close)
    ImageView ivMusicClose;
    @BindView(R.id.tv_music_title)
    MarqueeTextView tvMusicTitle;
    @BindView(R.id.tv_music_name)
    TextView tvMusicName;
    @BindView(R.id.iv_music_play_mode)
    ImageView ivMusicPlayMode;
    @BindView(R.id.iv_music_play_list)
    ImageView ivMusicPlayList;
    @BindView(R.id.iv_music_play_state)
    ImageView ivMusicPlayState;
    @BindView(R.id.iv_music_play_up)
    ImageView ivMusicPlayUp;
    @BindView(R.id.iv_music_play_down)
    ImageView ivMusicPlayDown;
    @BindView(R.id.tv_upload)
    TextView tvUpload;
    @BindView(R.id.seekbar)
    SeekBar seekBar;
    private Context mContext;

    /**
     * 0 顺序循序播放   1随机播放  2单曲循环
     */
    private int playPattern = 0;
    private MusicPlayClick musicPlayClick;
    public static final int TYPE_DEFAULT = 0x00;
    //0 暂停播放  1 开始播放
    private int play = 0;
    private MusicTable mMusicTable;


    public MusicPlayerDialog(@NonNull Context context) {
        this(context, TYPE_DEFAULT);
    }

    public MusicPlayerDialog(@NonNull Context context, int type) {
        super(context);
        this.mContext = context;
        initViews();
    }

    private View dialogView;

    private void initViews() {
        dialogView = LayoutInflater.from(mContext).inflate(R.layout.dialog_music_player, null, false);
        ButterKnife.bind(this, dialogView);
        setContentView(dialogView);
        ((View) dialogView.getParent()).setBackgroundColor(Color.parseColor("#00000000"));
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
                    if (musicPlayClick != null) {
                        musicPlayClick.setＭmusicvolume(progress);
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
        playPattern = SPUtil.getInt(Constant.PLAY_MODE, 0);
        ivMusicPlayMode.setImageLevel(playPattern);
    }

    public void setMusicPlayMode(int playPattern) {
        ivMusicPlayMode.setImageLevel(playPattern);
    }

    public void setMusicPlayState(int state) {
        ivMusicPlayState.setImageLevel(state);
    }

    public void setMusicVolume(int volume) {
        seekBar.setProgress(volume);
    }

    public void setMusic(MusicTable music) {
        mMusicTable = music;
        if (music == null) {
            tvMusicTitle.setText("暂无");
            tvMusicName.setText("-暂无-");
        } else {
            tvMusicTitle.setText(music.getTitle());
            tvMusicName.setText("-" + music.getAuthor() + "-");
        }
    }

    @OnClick({R.id.iv_music_close, R.id.iv_music_play_mode, R.id.iv_music_play_list, R.id.tv_upload, R.id.iv_music_play_up, R.id.iv_music_play_state, R.id.iv_music_play_down, R.id.iv_music_mute})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_music_close:
                if (musicPlayClick != null) {
                    musicPlayClick.close();
                }
                break;
            case R.id.iv_music_play_mode:
                if (mMusicTable == null) {
                    ToastUtils.showShort("请选择歌曲");
                    return;
                }
                if (musicPlayClick != null) {
                    if (playPattern == 0) {
                        playPattern = 1;
                    } else if (playPattern == 1) {
                        playPattern = 2;
                    } else {
                        playPattern = 0;
                    }
                    ivMusicPlayMode.setImageLevel(playPattern);
                    SPUtil.saveInt(Constant.PLAY_MODE, playPattern);
                    musicPlayClick.playPattern(playPattern);
                }
                break;
            case R.id.iv_music_play_list:
                if (musicPlayClick != null) {
                    musicPlayClick.playList(mContext);
                }
                break;
            case R.id.tv_upload:
                if (musicPlayClick != null) {
                    musicPlayClick.uoLoad();
                }
                break;
            case R.id.iv_music_play_up:
                if (musicPlayClick != null) {
                    musicPlayClick.lastSong();
                }
                break;
            case R.id.iv_music_play_state:
                if (musicPlayClick != null && mMusicTable != null) {
                    if (play == 0) {
                        play = 1;
                        musicPlayClick.playState(play);
                    } else {
                        play = 0;
                        musicPlayClick.playState(play);
                    }
                    ivMusicPlayState.setImageLevel(play);
                }
                break;
            case R.id.iv_music_play_down:
                if (musicPlayClick != null) {
                    musicPlayClick.nextSong();
                }
                break;
            case R.id.iv_music_mute:
                if (musicPlayClick != null) {
                    musicPlayClick.mute();
                }
                break;
        }
    }


    public void addMusicPlayClick(MusicPlayClick musicPlayClick) {
        this.musicPlayClick = musicPlayClick;
    }

    public interface MusicPlayClick {
        void close();

        void playPattern(int playPattern);

        void lastSong();

        void nextSong();

        void uoLoad();

        void playList(Context context);

        void playState(int play);

        void mute();

        void setＭmusicvolume(int ｍmusicvolume);
    }


}
