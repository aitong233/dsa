package com.qpyy.libcommon.widget.voice;


import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qpyy.libcommon.R;
import com.qpyy.libcommon.R2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 录音控件button
 */
public class RecordVoiceButton extends LinearLayout {

    @BindView(R2.id.ll)
    LinearLayout mLLVoice;
    @BindView(R2.id.iv_voice_start)
    ImageView mIvVoiceStart;
    @BindView(R2.id.iv_voice_stop)
    ImageView mIvVoiceStop;
    @BindView(R2.id.tv_voice_state)
    TextView mTvVoiceState;
    @BindView(R2.id.iv_delete)
    ImageView mIvDelete;
    private EnRecordVoiceListener enRecordVoiceListener;
    private VoiceManager voiceManager;
    private String recordFilePath;

    public RecordVoiceButton(Context context) {
        super(context);
        init(context);
    }

    public RecordVoiceButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public RecordVoiceButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        voiceManager = VoiceManager.getInstance();
        LayoutInflater.from(context).inflate(R.layout.ll_record_voice, this);
        ButterKnife.bind(this);
        voiceManager.setVoicePlayListener(new VoiceManager.VoicePlayCallBack() {
            @Override
            public void voiceTotalLength(long time, String strTime) {
                mIvDelete.setVisibility(VISIBLE);
                mLLVoice.setBackgroundResource(R.drawable.bg_r99_gradient_main_stroke);
                mIvVoiceStop.setVisibility(GONE);
                mIvVoiceStart.setVisibility(VISIBLE);
                mTvVoiceState.getPaint().setFakeBoldText(false);
                mTvVoiceState.setText(strTime);
                mTvVoiceState.setTextColor(Color.parseColor("#6765FF"));
            }

            @Override
            public void playDoing(long time, String strTime) {
                mLLVoice.setBackgroundResource(R.drawable.bg_r99_gradient_main_sold);
                mIvVoiceStop.setVisibility(VISIBLE);
                mIvVoiceStart.setVisibility(GONE);
                mTvVoiceState.getPaint().setFakeBoldText(false);
                mTvVoiceState.setTextColor(Color.WHITE);
                mTvVoiceState.setText(strTime);
            }

            @Override
            public void playPause() {

            }

            @Override
            public void playStart() {
                mLLVoice.setBackgroundResource(R.drawable.bg_r99_gradient_main_sold);
                mIvVoiceStop.setVisibility(VISIBLE);
                mIvVoiceStart.setVisibility(GONE);
                mTvVoiceState.getPaint().setFakeBoldText(false);
                mTvVoiceState.setTextColor(Color.WHITE);
            }

            @Override
            public void playFinish() {
                mLLVoice.setBackgroundResource(R.drawable.bg_r99_gradient_main_stroke);
                mIvVoiceStop.setVisibility(GONE);
                mIvVoiceStart.setVisibility(VISIBLE);
                mTvVoiceState.getPaint().setFakeBoldText(false);
                mTvVoiceState.setTextColor(Color.parseColor("#6765FF"));
            }
        });
        voiceManager.setVoiceRecordListener(new VoiceManager.VoiceRecordCallBack() {
            @Override
            public void recDoing(long time, String strTime) {
                mLLVoice.setBackgroundResource(R.drawable.bg_r99_gradient_main_sold);
                mIvVoiceStop.setVisibility(VISIBLE);
                mIvVoiceStart.setVisibility(GONE);
                mTvVoiceState.getPaint().setFakeBoldText(false);
                mTvVoiceState.setTextColor(Color.WHITE);
                mTvVoiceState.setText(strTime);
            }

            @Override
            public void recVoiceGrade(int grade) {

            }

            @Override
            public void recStart(boolean init) {
                mLLVoice.setBackgroundResource(R.drawable.bg_r99_gradient_main_sold);
                mIvVoiceStop.setVisibility(VISIBLE);
                mIvVoiceStart.setVisibility(GONE);
                mTvVoiceState.getPaint().setFakeBoldText(false);
                mTvVoiceState.setTextColor(Color.WHITE);
            }

            @Override
            public void recPause(String str) {

            }


            @Override
            public void recFinish(long length, String strLength, String path) {
                if (length == 0) {
                    recordFilePath = null;
                    mTvVoiceState.setText("开始录音");
                    mLLVoice.setBackgroundResource(R.drawable.bg_r99_gradient_main_stroke);
                    mIvDelete.setVisibility(GONE);
                    mIvVoiceStop.setVisibility(GONE);
                    mIvVoiceStart.setVisibility(VISIBLE);
                    mTvVoiceState.getPaint().setFakeBoldText(true);
                    mTvVoiceState.setTextColor(Color.parseColor("#6765FF"));
                } else {
                    {
                        mLLVoice.setBackgroundResource(R.drawable.bg_r99_gradient_main_stroke);
                        mIvDelete.setVisibility(VISIBLE);
                        mIvVoiceStop.setVisibility(GONE);
                        mIvVoiceStart.setVisibility(VISIBLE);
                        mTvVoiceState.getPaint().setFakeBoldText(false);
                        mTvVoiceState.setTextColor(Color.parseColor("#6765FF"));
                        recordFilePath = path;
                        if (enRecordVoiceListener != null) {
                            enRecordVoiceListener.onFinishRecord(length, strLength, path);
                        }
                    }
                }
            }
        });
    }

    /**
     * 设置监听
     *
     * @param enRecordVoiceListener
     */
    public void setEnrecordVoiceListener(EnRecordVoiceListener enRecordVoiceListener) {
        this.enRecordVoiceListener = enRecordVoiceListener;
    }


    @OnClick({R2.id.ll, R2.id.iv_delete})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.ll) {
            voiceManager.startRecordOrPlay(recordFilePath);
        } else if (id == R.id.iv_delete) {
            voiceManager.stopPlay();
            recordFilePath = null;
            mTvVoiceState.setText("开始录音");
            mLLVoice.setBackgroundResource(R.drawable.bg_r99_gradient_main_stroke);
            mIvDelete.setVisibility(GONE);
            mIvVoiceStop.setVisibility(GONE);
            mIvVoiceStart.setVisibility(VISIBLE);
            mTvVoiceState.getPaint().setFakeBoldText(true);
            mTvVoiceState.setTextColor(Color.parseColor("#6765FF"));
            if (enRecordVoiceListener != null) {
                enRecordVoiceListener.clearVoice();
            }
        }
    }

    public void clear() {
        voiceManager.stopPlay();
        recordFilePath = null;
        mTvVoiceState.setText("开始录音");
        mLLVoice.setBackgroundResource(R.drawable.bg_r99_gradient_main_stroke);
        mIvDelete.setVisibility(GONE);
        mIvVoiceStop.setVisibility(GONE);
        mIvVoiceStart.setVisibility(VISIBLE);
        mTvVoiceState.getPaint().setFakeBoldText(true);
        mTvVoiceState.setTextColor(Color.parseColor("#6765FF"));
    }

    /**
     * 结束回调监听
     */
    public interface EnRecordVoiceListener {
        void clearVoice();

        void onFinishRecord(long length, String strLength, String filePath);
    }

    public void releaseVoiceManager() {
        if (voiceManager != null) {
            voiceManager.release();
        }
    }

    public void setRecordFilePath(String recordFilePath) {
        this.recordFilePath = recordFilePath;
        voiceManager.preparePlay(recordFilePath);
    }
}
