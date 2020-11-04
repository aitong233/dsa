package com.qpyy.room.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import com.blankj.utilcode.util.ThreadUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.integration.webp.decoder.WebpDrawable;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.opensource.svgaplayer.SVGACallback;
import com.opensource.svgaplayer.SVGADrawable;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.SVGAVideoEntity;
import com.qpyy.libcommon.utils.LogUtils;
import com.qpyy.libcommon.utils.SpUtils;
import com.qpyy.room.R;
import com.qpyy.room.R2;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Queue;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.widget
 * 创建人 王欧
 * 创建时间 2020/8/16 1:56 PM
 * 描述 describe
 */
public class SvgaAnimView extends ConstraintLayout {
    @BindView(R2.id.image)
    SVGAImageView mSVGAImageView;

    private boolean isPlaying;

    private Queue<String> queue = new LinkedList<>();

    public SvgaAnimView(Context context) {
        this(context, null);
    }

    public SvgaAnimView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.room_view_svga_anim, this);
        ButterKnife.bind(this);
    }


    public void load(String url) {
        //判断是否开启特效显示
        if (SpUtils.getOpenEffect() == 0) {
            return;
        }
        if (queue == null) {
            return;
        }
        if (isPlaying) {
            queue.add(url);
        } else {
            playAnimation(url);
        }
    }

    private void showAnim() {
        if (SpUtils.getOpenEffect() == 0) {
            return;
        }
        if (!isEmpty()) {
            playAnimation(queue.poll());
        }
    }

    private void playAnimation(String url) {
        try {
            isPlaying = true;
            SVGAParser svgaParser = SVGAParser.Companion.shareParser();
            if (!TextUtils.isEmpty(url)) {
                if (url.endsWith("webp")) {
                    Glide.with(this).load(url).addListener(new RequestListener<Drawable>() {

                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            isPlaying = false;
                            showAnim();
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            setLoopCount((WebpDrawable) resource, 1);
                            int delay = getWebpPlayTime(resource);
                            ThreadUtils.runOnUiThreadDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    isPlaying = false;
                                    showAnim();
                                }
                            }, delay);
                            return false;
                        }

                    }).into(mSVGAImageView);


                    return;
                }
                svgaParser.decodeFromURL(new URL(url), new SVGAParser.ParseCompletion() {
                    @Override
                    public void onComplete(@NotNull SVGAVideoEntity svgaVideoEntity) {
                        SVGADrawable svgaDrawable = new SVGADrawable(svgaVideoEntity);
                        mSVGAImageView.setImageDrawable(svgaDrawable);
                        mSVGAImageView.startAnimation();
                        mSVGAImageView.setCallback(new SVGACallback() {
                            @Override
                            public void onPause() {
                                LogUtils.e("动画", "播放暂停");
                            }

                            @Override
                            public void onFinished() {
                                isPlaying = false;
                                LogUtils.e("动画", "播放结束");
                                showAnim();
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
                        showAnim();
                        LogUtils.e("动画", "播放错误");
                    }
                });
            }
        } catch (MalformedURLException e) {
            isPlaying = false;
            e.printStackTrace();
            LogUtils.e("动画", "播放错误" + e.getMessage());
        }
    }

    private void setLoopCount(WebpDrawable resource, int count) {
        resource.setLoopCount(count);
    }

    private int getWebpPlayTime(Drawable resource) {
        WebpDrawable webpDrawable = (WebpDrawable) resource;
        try {
            Field gifStateField = WebpDrawable.class.getDeclaredField("state");
            gifStateField.setAccessible(true);
            Class gifStateClass = Class.forName("com.bumptech.glide.integration.webp.decoder.WebpDrawable$WebpState");
            Field gifFrameLoaderField = gifStateClass.getDeclaredField("frameLoader");
            gifFrameLoaderField.setAccessible(true);

            Class gifFrameLoaderClass = Class.forName("com.bumptech.glide.integration.webp.decoder.WebpFrameLoader");
            Field gifDecoderField = gifFrameLoaderClass.getDeclaredField("webpDecoder");
            gifDecoderField.setAccessible(true);

            Class gifDecoderClass = Class.forName("com.bumptech.glide.integration.webp.decoder.WebpDecoder");
            Object gifDecoder = gifDecoderField.get(gifFrameLoaderField.get(gifStateField.get(resource)));
            Method getDelayMethod = gifDecoderClass.getDeclaredMethod("getDelay", int.class);
            getDelayMethod.setAccessible(true);
            // 设置只播放一次
            // 获得总帧数
            int count = webpDrawable.getFrameCount();
            int delay = 0;
            for (int i = 0; i < count; i++) {
                // 计算每一帧所需要的时间进行累加
                delay += (int) getDelayMethod.invoke(gifDecoder, i);
            }
            return delay;

        } catch (Exception e) {
            isPlaying = false;
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 礼物是否为空
     */
    public boolean isEmpty() {
        return queue == null || queue.size() == 0;
    }

    /**
     * 关闭特效
     */
    public void closeEffect(){
        //清空队列
        queue.clear();
        //关闭动画
        if(mSVGAImageView !=null && isPlaying && mSVGAImageView.isAnimating()){
            mSVGAImageView.setAnimation(null);
            mSVGAImageView.clearAnimation();
            mSVGAImageView.stopAnimation();
        }
    }
}
