//package com.qpyy.room.dialog;
//
//import android.content.Context;
//import android.support.annotation.NonNull;
//import android.support.constraint.ConstraintLayout;
//import android.text.TextUtils;
//import android.util.Log;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.RelativeLayout;
//import android.widget.SeekBar;
//
//import com.hjq.toast.ToastUtils;
//import com.qpyy.libcommon.constant.SPConstants;
//import com.qpyy.libcommon.constant.URLConstants;
//import com.qpyy.libcommon.db.table.MusicTable;
//import com.qpyy.libcommon.utils.SpUtils;
//import com.qpyy.libcommon.widget.MarqueeTextView;
//import com.qpyy.libcommon.widget.dialog.BaseDialog;
//import com.qpyy.room.R;
//import com.qpyy.room.R2;
//
//import butterknife.BindView;
//import butterknife.OnClick;
//
///**
// * 项目名称 qipao-android
// * 包名：com.qpyy.room.dialog
// * 创建人 黄强
// * 创建时间 2020/8/7 14:57
// * 描述 describe
// */
//public class MusicPlayWindowDialog extends BaseDialog {
//
//    private static final String TAG = "MusicPlayWindowDialog";
//    @BindView(R2.id.tv_music_title)
//    MarqueeTextView tvMusicTitle;
//    @BindView(R2.id.tv_singer)
//    MarqueeTextView tvSinger;
//    @BindView(R2.id.iv_minx)
//    ImageView ivMinx;
//    @BindView(R2.id.iv_pattern)
//    ImageView ivPattern;
//    @BindView(R2.id.iv_last)
//    ImageView ivLast;
//    @BindView(R2.id.iv_music_play_state)
//    ImageView ivMusicPlayState;
//    @BindView(R2.id.iv_next)
//    ImageView ivNext;
//    @BindView(R2.id.iv_list)
//    ImageView ivList;
//    @BindView(R2.id.cl_music_btn_layout)
//    ConstraintLayout clMusicBtnLayout;
//    @BindView(R2.id.seek_bar)
//    SeekBar seekBar;
//    @BindView(R2.id.rl_music_name_msg)
//    RelativeLayout rlMusicNameMsg;
//    @BindView(R.id.tv_music_title)
//    MarqueeTextView tvMusicTitle;
//    @BindView(R.id.tv_singer)
//    MarqueeTextView tvSinger;
//    @BindView(R.id.iv_minx)
//    ImageView ivMinx;
//    @BindView(R.id.iv_pattern)
//    ImageView ivPattern;
//    @BindView(R.id.iv_last)
//    ImageView ivLast;
//    @BindView(R.id.iv_music_play_state)
//    ImageView ivMusicPlayState;
//    @BindView(R.id.iv_next)
//    ImageView ivNext;
//    @BindView(R.id.iv_list)
//    ImageView ivList;
//    @BindView(R.id.cl_music_btn_layout)
//    ConstraintLayout clMusicBtnLayout;
//    @BindView(R.id.seek_bar)
//    SeekBar seekBar;
//    @BindView(R.id.rl_music_name_msg)
//    RelativeLayout rlMusicNameMsg;
//
//    /**
//     * 0 顺序循序播放   1随机播放  2单曲循环
//     */
//    private int playPattern = 0;
//    private MusicPlayClick musicPlayClick;
//    public static final int TYPE_DEFAULT = 0x00;
//    //0 暂停播放  1 开始播放
//    private int play = 0;
//    private MusicTable mMusicTable;
//
//
//    public MusicPlayWindowDialog(@NonNull Context context) {
//        super(context);
//        Log.d(TAG, "(Start)启动了===========================MusicPlayWindowDialog");
//    }
//
//    @Override
//    public int getLayout() {
//        return R.layout.room_dialog_music_window_open;
//    }
//
//    @Override
//    public void initView() {
//
//    }
//
//    @Override
//    public void initData() {
//
//    }
//
//    @OnClick({R2.id.tv_music_title, R2.id.tv_singer, R2.id.iv_minx, R2.id.iv_pattern, R2.id.iv_last, R2.id.iv_music_play_state, R2.id.iv_next, R2.id.iv_list})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.tv_music_title:
//                break;
//            case R.id.tv_singer:
//                break;
//            case R.id.iv_minx:
//                break;
//            case R.id.iv_pattern:
//                if (mMusicTable == null) {
//                    ToastUtils.show("请选择歌曲");
//                    return;
//                }
//                if (musicPlayClick != null) {
//                    if (playPattern == 0) {
//                        playPattern = 1;
//                    } else if (playPattern == 1) {
//                        playPattern = 2;
//                    } else {
//                        playPattern = 0;
//                    }
//                    ivPattern.setImageLevel(playPattern);
//                    SpUtils.setPlayPattern(playPattern);
//                    musicPlayClick.playPattern(playPattern);
//                }
//                break;
//            case R.id.iv_last:
//                break;
//            case R.id.iv_music_play_state:
//                break;
//            case R.id.iv_next:
//                break;
//            case R.id.iv_list:
//                break;
//        }
//    }
//
//    public interface MusicPlayClick {
//        void close();
//
//        void playPattern(int playPattern);
//
//        void lastSong();
//
//        void nextSong();
//
//        void uoLoad();
//
//        void playList(Context context);
//
//        void playState(int play);
//
//        void mute();
//
//        void setＭmusicvolume(int ｍmusicvolume);
//    }
//
//}
