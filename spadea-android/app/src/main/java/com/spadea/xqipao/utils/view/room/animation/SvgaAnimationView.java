package com.spadea.xqipao.utils.view.room.animation;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.opensource.svgaplayer.SVGACallback;
import com.opensource.svgaplayer.SVGADrawable;
import com.opensource.svgaplayer.SVGADynamicEntity;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.SVGAVideoEntity;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.utilcode.ConvertUtils;
import com.spadea.xqipao.data.SvgaModel;
import com.spadea.xqipao.utils.LogUtils;

import org.jetbrains.annotations.NotNull;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Queue;

public class SvgaAnimationView extends FrameLayout {


    private SVGAImageView svgaImageView;
    private SVGAImageView svgaApproach;
    private static Queue<SvgaModel> queue = new LinkedList<SvgaModel>();
    private boolean isPlaying = false;


    public SvgaAnimationView(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public SvgaAnimationView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public SvgaAnimationView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_svga_animation, this, true);
        svgaImageView = findViewById(R.id.iv_ripple1);
        svgaApproach = findViewById(R.id.iv_approach);
    }

    public void initQueue() {
        queue = new LinkedList<>();
    }

    public void emptyQueue() {
        if (queue != null) {
            queue.clear();
            queue = null;
        }
    }

    public void load(SvgaModel svgaModel) {
        if (queue == null) {
            queue = new LinkedList<>();
        }
        if (isPlaying) {
            queue.add(svgaModel);
        } else {
            playAnimation(svgaModel);
        }
    }

    public void load(String url) {
        if (queue == null) {
            return;
        }
        SvgaModel svgaModel = new SvgaModel(url);
        if (isPlaying) {
            queue.add(svgaModel);
        } else {
            playAnimation(svgaModel);
        }
    }

    private void playAnimation(SvgaModel model) {
        LogUtils.e("动画", "播放开始");
        isPlaying = true;
        showGift(model);
        showApproach(model);
    }

    private void showGift(SvgaModel model) {
        try {
            SVGAParser svgaParser = SVGAParser.Companion.shareParser();
            if (!TextUtils.isEmpty(model.url)) {
                svgaParser.decodeFromURL(new URL(model.url), new SVGAParser.ParseCompletion() {
                    @Override
                    public void onComplete(@NotNull SVGAVideoEntity svgaVideoEntity) {
                        SVGADrawable svgaDrawable;
                        if (model.type == SvgaModel.TYPE_JUE || model.type == SvgaModel.TYPE_JUE_AND_APPROACH) {
                            SVGADynamicEntity dynamicEntity = new SVGADynamicEntity();
                            TextPaint textPaint = new TextPaint();
                            textPaint.setAntiAlias(true);
                            textPaint.setColor(Color.WHITE);
                            textPaint.setFakeBoldText(true);
                            textPaint.setTextSize(ConvertUtils.sp2px(6));
                            dynamicEntity.setDynamicText(String.format("欢迎%s%s进入房间", model.nobilityName, model.userName), textPaint, "textarea");
                            svgaDrawable = new SVGADrawable(svgaVideoEntity, dynamicEntity);
                        } else {
                            svgaDrawable = new SVGADrawable(svgaVideoEntity);
                        }
                        if (svgaImageView == null) {
                            isPlaying = false;
                            return;
                        }
                        svgaImageView.setImageDrawable(svgaDrawable);
                        svgaImageView.startAnimation();
                        svgaImageView.setCallback(new SVGACallback() {
                            @Override
                            public void onPause() {
                                LogUtils.e("动画", "播放暂停");
                            }

                            @Override
                            public void onFinished() {
                                isPlaying = false;
                                LogUtils.e("动画", "播放结束");
                                if (!isEmpty()) {
                                    playAnimation(queue.poll());
                                }
                            }

                            @Override
                            public void onRepeat() {

                            }

                            @Override
                            public void onStep(int i, double v) {

                            }
                        });
                    }

                    @Override
                    public void onError() {
                        isPlaying = false;
                        LogUtils.e("动画", "播放错误");
                    }
                });
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            LogUtils.e("动画", "播放错误" + e.getMessage());
        }
    }

    private void showApproach(SvgaModel model) {
        try {
            if (!TextUtils.isEmpty(model.approachUrl)) {
                SVGAParser svgaParser = SVGAParser.Companion.shareParser();
                svgaParser.decodeFromURL(new URL(model.approachUrl), new SVGAParser.ParseCompletion() {
                    @Override
                    public void onComplete(@NotNull SVGAVideoEntity svgaVideoEntity) {
                        SVGADrawable svgaDrawable = new SVGADrawable(svgaVideoEntity);
                        if (svgaApproach == null) {
                            if (TextUtils.isEmpty(model.url)) {
                                isPlaying = false;
                            }
                            return;
                        }
                        svgaApproach.setImageDrawable(svgaDrawable);
                        svgaApproach.startAnimation();
                        svgaApproach.setCallback(new SVGACallback() {
                            @Override
                            public void onPause() {
                                LogUtils.e("坐骑动画", "播放暂停");
                            }

                            @Override
                            public void onFinished() {
                                LogUtils.e("坐骑动画", "播放结束");
                                if (TextUtils.isEmpty(model.url)) {
                                    isPlaying = false;
                                    if (!isEmpty()) {
                                        playAnimation(queue.poll());
                                    }
                                }
                            }

                            @Override
                            public void onRepeat() {

                            }

                            @Override
                            public void onStep(int i, double v) {

                            }
                        });
                    }

                    @Override
                    public void onError() {
                        LogUtils.e("坐骑动画", "播放错误");
                        if (TextUtils.isEmpty(model.url)) {
                            isPlaying = false;
                        }
                    }
                });
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 礼物是否为空
     */
    public boolean isEmpty() {
        return queue == null || queue.size() == 0;
    }
}
