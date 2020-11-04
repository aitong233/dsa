package com.qpyy.libcommon.widget.voice;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qpyy.libcommon.R;
import com.qpyy.libcommon.R2;
import com.qpyy.libcommon.utils.MediaPlayerUtiles;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 录音控件button
 */
public class RecordVoiceView extends LinearLayout {


    @BindView(R2.id.iv_voice_start)
    ImageView ivVoiceStart;
    @BindView(R2.id.tv_voice_state)
    TextView tvVoiceState;
    @BindView(R2.id.ll)
    LinearLayout ll;
    @BindView(R2.id.iv_delete)
    ImageView ivDelete;


    private String mUrl;
    private boolean b = false;
    private EnRecordVoiceListener mEnRecordVoiceListener;

    public RecordVoiceView(Context context) {
        super(context);
        init(context);
    }

    public RecordVoiceView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public RecordVoiceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.ll_record_voice_view, this);
        ButterKnife.bind(this);
    }


    public void setVoiceData(String url, String time) {
        setTime(time, tvVoiceState);
        mUrl = url;
    }

    public void setVoiceTime(String time) {
        setTime(time, tvVoiceState);
    }

    @OnClick({R2.id.ll, R2.id.iv_delete})
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.ll) {
            if (b) {
                MediaPlayerUtiles.getInstance().stopAudio();
                b = false;
                if (ivVoiceStart != null) {
                    ivVoiceStart.setImageResource(R.mipmap.icon_play);
                    if (mEnRecordVoiceListener != null) {
                        mEnRecordVoiceListener.onCompletion();
                    }
                }
            } else {
                MediaPlayerUtiles.getInstance().playAudio( mUrl, new MediaPlayerUtiles.OnMediaPlayerListener() {
                    @Override
                    public void onStartPlay() {
                        if (ivVoiceStart != null) {
                            b = true;
                            ivVoiceStart.setImageResource(R.mipmap.icon_pause);
                            if (mEnRecordVoiceListener != null) {
                                mEnRecordVoiceListener.onStartPlay();
                            }
                        }
                    }

                    @Override
                    public void onCompletion() {
                        if (ivVoiceStart != null) {
                            b = false;
                            ivVoiceStart.setImageResource(R.mipmap.icon_play);
                            if (mEnRecordVoiceListener != null) {
                                mEnRecordVoiceListener.onCompletion();
                            }
                        }
                    }
                });
            }
        } else if (id == R.id.iv_delete) {
            MediaPlayerUtiles.getInstance().stopAudio();
            if (mEnRecordVoiceListener != null) {
                mEnRecordVoiceListener.clearVoice();
            }
        }
    }

    public void addEnRecordVoiceListener(EnRecordVoiceListener enRecordVoiceListener) {
        mEnRecordVoiceListener = enRecordVoiceListener;
    }

    public interface EnRecordVoiceListener {
        void clearVoice();

        void onStartPlay();

        void onCompletion();
    }


    private void setTime(String date, TextView textView) {
        int seconds = Integer.parseInt(date);
        int temp = 0;
        StringBuffer sb = new StringBuffer();
        temp = seconds % 3600 / 60;
        sb.append((temp < 10) ? "0" + temp + ":" : "" + temp + ":");
        temp = seconds % 3600 % 60;
        sb.append((temp < 10) ? "0" + temp : "" + temp);
        textView.setText(sb.toString());
    }

}
