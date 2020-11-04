package com.qpyy.libcommon.utils;

import android.media.MediaPlayer;
import android.util.Log;


public class MediaPlayerUtiles {
    private MediaPlayer mediaPlayer;

    private static MediaPlayerUtiles instance = new MediaPlayerUtiles();

    public static MediaPlayerUtiles getInstance() {
        return instance;
    }

    private MediaPlayerUtiles() {
        if (null == mediaPlayer) {
            mediaPlayer = new MediaPlayer();
        }
    }

    private OnMediaPlayerListener mOnMediaPlayerListener;

    /**
     * 播放音频
     */
    public void playAudio(String url, OnMediaPlayerListener onMediaPlayerListener) {
        try {
            stopAudio();//如果正在播放就停止
            this.mOnMediaPlayerListener = onMediaPlayerListener;
            mediaPlayer.reset();
            mediaPlayer.setDataSource(url); // 设置数据源
            mediaPlayer.setLooping(false);//循环播放
            mediaPlayer.prepare();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                    if (mOnMediaPlayerListener != null) {
                        mOnMediaPlayerListener.onStartPlay();
                    }
                }
            });
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    if (mOnMediaPlayerListener != null) {
                        mOnMediaPlayerListener.onCompletion();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("播放音频失败", "");
        }
    }


    /**
     * 停止播放音频
     */
    public void stopAudio() {
        try {
            if (null != mediaPlayer) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    mediaPlayer.reset();
                    mediaPlayer.stop();
                }
            }
            this.mOnMediaPlayerListener = null;
        } catch (Exception e) {
            Log.e("stopAudio", e.getMessage());
        }
    }

    public interface OnMediaPlayerListener {
        void onStartPlay();

        void onCompletion();
    }

}
