package com.qpyy.module.me.widget;

import android.content.Context;
import android.util.AttributeSet;

import com.qpyy.module.me.R;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.shuyu.gsyvideoplayer.video.base.GSYBaseVideoPlayer;

/**
 * Created by witness on 2018/4/18.
 * <p>
 * 视频详情页面的视频播放器的自定义样式
 */

public class RecommendationVideo extends StandardGSYVideoPlayer {

    public RecommendationVideo(Context context, Boolean fullFlag) {
        super(context, fullFlag);
    }

    public RecommendationVideo(Context context) {
        super(context);
    }

    public RecommendationVideo(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int getLayoutId() {
        return R.layout.me_video_layout_recommendation;
    }

    @Override
    protected void init(Context context) {
        super.init(context);
    }

    //获取当前是否横竖屏
    public boolean ifCurrentIsFullscreen(){
        return mIfCurrentIsFullscreen;
    }

    public void setIsFullscreen(Boolean b){
        setIfCurrentIsFullscreen(b);
    }

    @Override
    public GSYBaseVideoPlayer startWindowFullscreen(Context context, boolean actionBar, boolean statusBar) {
        return super.startWindowFullscreen(context, actionBar, statusBar);
    }

    @Override
    public void clearFullscreenLayout() {
        super.clearFullscreenLayout();
    }
}

